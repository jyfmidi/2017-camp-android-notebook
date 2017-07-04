package com.example.jyfmi.memo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by jyfmi on 2017/7/4.
 */

public class NoteAdapter extends ArrayAdapter<Note> {

    public static final int WRAP_CONTENT_LENGTH = 50;

    public NoteAdapter(Context context, int resource, ArrayList<Note> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_note, null);
        }

        Note note = getItem(position);

        if(note != null) {
            TextView title = (TextView) convertView.findViewById(R.id.list_note_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_note_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_note_content);

            title.setText(note.getTitle());
            date.setText(note.getDateTimeFormatted(getContext()));

            if(note.getContent().length() >  50){
                content.setText(note.getContent().substring(0,50));
            }
            else{
                content.setText(note.getContent());
            }
        }

        return convertView;

    }
}
