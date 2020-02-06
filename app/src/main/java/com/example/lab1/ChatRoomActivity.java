package com.example.lab1;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private MyListAdapter adapter;
    private ListView listConv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        editText = findViewById(R.id.editTextChatMsg);
        listConv = findViewById(R.id.listConversation);
        adapter = new MyListAdapter(this, R.id.listConversation);
        listConv.setAdapter(adapter);
        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);

        Button buttonReceived = findViewById(R.id.buttonReceive);
        buttonReceived.setOnClickListener(this);

        listConv.setOnItemLongClickListener( (p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this message?")
                    .setMessage("the row is"+pos)
                    //what the Yes button does:
                    .setPositiveButton("Yes", (click, arg) -> {
                    adapter.remove(adapter.getItem(pos));
                    })
                    //What the No button does:
                    .setNegativeButton("No", (click, arg) -> { })

                    //Show the dialog
                    .create().show();
            return true;
        });    }

    @Override
    public void onClick(View v) {
        String input = editText.getText().toString();

        if (input.length() == 0)
            return;

        switch (v.getId()) {
            case R.id.buttonSend:
                adapter.add(new Message(input, MessageType.SENT));

                break;
            case R.id.buttonReceive:
                adapter.add(new Message(input, MessageType.RECEIVED));

                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
        editText.setText("");
    }

    enum MessageType { SENT, RECEIVED }


}