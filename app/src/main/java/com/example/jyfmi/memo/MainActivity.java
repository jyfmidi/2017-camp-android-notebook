package com.example.jyfmi.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListNotes = (ListView) findViewById(R.id.main_listview_notes);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_main_new_note: //run NoteActivity in new note mode
                startActivity(new Intent(this, NoteActivity.class));
                break;

//            case R.id.action_settings:
//                //TODO show settings activity
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListNotes.setAdapter(null);
        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);

        if(notes == null || notes.size() == 0){
            Toast.makeText(this, "You have no saved notes!",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            NoteAdapter na = new NoteAdapter(this, R.layout.item_note, notes);
            mListNotes.setAdapter(na);

            mListNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String fileName = ((Note)mListNotes.getItemAtPosition(position)).getDateTime()
                        + Utilities.FILE_EXTENSION;

                    Intent viewNoteIntent = new Intent(getApplicationContext(), NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE", fileName);
                    startActivity(viewNoteIntent);

                }
            });
        }
    }
}
