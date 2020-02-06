package com.example.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<Message> {
    private LayoutInflater inflater;


    MyListAdapter(Context context, int resource) {
        super(context, resource);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getItem(position);

        View view;
        TextView textView;

        if (message.getType() == ChatRoomActivity.MessageType.SENT) {
            view = inflater.inflate(R.layout.chat_message_sent, null);
            textView = view.findViewById(R.id.textViewSent);
            textView.setText(message.getMessage());
            return view;

        } else if (message.getType() == ChatRoomActivity.MessageType.RECEIVED) {
            view = inflater.inflate(R.layout.chat_message_received, null);
            textView = view.findViewById(R.id.textViewReceived);
            textView.setText(message.getMessage());
            return view;
        }

        return null;
    }
}