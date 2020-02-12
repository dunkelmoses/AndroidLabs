package com.example.lab1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<Message> {
    private LayoutInflater inflater;
    public static final String ACTIVITY_NAME = "CHATROOM_ACTIVITY";


    MyListAdapter(Context context, int resource) {
        super(context, resource);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        View view;
        TextView textView;

        if (message.getType().equals("SENT")) {
            view = inflater.inflate(R.layout.chat_message_sent, null);
            textView = view.findViewById(R.id.textViewSent);
            textView.setText(message.getMessage());
            return view;

        } else if (message.getType().equals("RECEIVED")) {
            view = inflater.inflate(R.layout.chat_message_received, null);
            textView = view.findViewById(R.id.textViewReceived);
            textView.setText(message.getMessage());
            return view;
        }

        return null;
    }
    public void printCursor(Cursor cursor) {

        int columnNumber = cursor.getColumnCount();
        Log.i(ACTIVITY_NAME, "Column number: " + columnNumber);

        for (int i = 0; i < columnNumber; ++i) {
            Log.i(ACTIVITY_NAME, "Column[" + i + "] name:" + cursor.getColumnName(i));
        }

        int rows = cursor.getCount();
        Log.i(ACTIVITY_NAME, "There are " + rows + " rows in cursor");

        while (cursor.moveToNext()) {
            StringBuilder string = new StringBuilder();
            for (int j = 0; j < columnNumber; ++j)
                string.append(cursor.getString(j) + " ");
            Log.i(ACTIVITY_NAME, string.toString());
        }
    }
}