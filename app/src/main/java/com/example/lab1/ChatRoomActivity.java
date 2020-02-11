package com.example.lab1;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private MyDatabaseOpenHelper dbOpener;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        dbOpener = new MyDatabaseOpenHelper(this);
        db = dbOpener.getWritableDatabase();

        editText = findViewById(R.id.editTextChatMsg);
        listConv = findViewById(R.id.listConversation);
        adapter = new MyListAdapter(this, R.id.listConversation);
        listConv.setAdapter(adapter);
        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(this);

        Button buttonReceived = findViewById(R.id.buttonReceive);
        buttonReceived.setOnClickListener(this);

        String[] columns = {MyDatabaseOpenHelper.COL_ID, MyDatabaseOpenHelper.COL_MESSAGE, MyDatabaseOpenHelper.COL_MESSAGE_TYPE};
        Cursor results = db.query(false, MyDatabaseOpenHelper.TABLE_NAME, columns, null, null, null, null, null, null);

        int messageTypeIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGE_TYPE);
        int messageIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGE);
        int idColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_ID);

        //iterate over the results, return true if there is a next item:
        results.moveToFirst();
        while (results.moveToNext()) {
            String message = results.getString(messageIndex);
            String messageType = results.getString(messageTypeIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            if (messageType.equals("SENT")) {
                adapter.add(new Message(id, message, MessageType.SENT));
            } else {
                adapter.add(new Message(id, message, MessageType.RECEIVED));
            }
        }


        listConv.setOnItemLongClickListener( (p, b, pos, id) -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this message?")
                    .setMessage(getString(R.string.rowName)+" "+pos + "\n" +
                    getString(R.string.databaseName)+id)
                    //what the Yes button does:
                    .setPositiveButton("Yes", (click, arg) -> {
                    adapter.remove(adapter.getItem(pos));
                    })
                    //What the No button does:
                    .setNegativeButton("No", (click, arg) -> { })

                    //Show the dialog
                    .create().show();
            return true;
        });
    }

    @Override
    public void onClick(View v) {
        String input = editText.getText().toString();

        if (input.length() == 0)
            return;

        ContentValues newRowValues = new ContentValues();
        newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGE, input);


        long newId = 0;

        switch (v.getId()) {
            case R.id.buttonSend:
                newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGE_TYPE, "SENT");
                newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);
                adapter.add(new Message(newId, input, MessageType.SENT));
                break;
            case R.id.buttonReceive:
                newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGE_TYPE, "RECEIVED");
                newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);
                adapter.add(new Message(newId, input, MessageType.RECEIVED));
                break;
            default:
                break;
        }
        editText.setText("");
    }
    enum MessageType { SENT, RECEIVED }
}
