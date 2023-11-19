package com.example.notesapp_lab4;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvNotes;
    ArrayList<notes> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = findViewById(R.id.lvNotes);
        SharedPreferences sharedPref = this.getSharedPreferences(constants.NOTES_FILE, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPref.getString(constants.NOTE_LIST_KEY, null);

        Type type = new TypeToken<ArrayList<notes>>() {}.getType();
        arrayList = gson.fromJson(json, type);

        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }

        notes_adapter noteAdapter = new notes_adapter(this, R.layout.list_rows, arrayList);
        lvNotes.setAdapter(noteAdapter);
        Log.e("App", "NoteApp successfully started!");
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPref = this.getSharedPreferences(constants.NOTES_FILE, MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPref.getString(constants.NOTE_LIST_KEY, null);

        Type type = new TypeToken<ArrayList<notes>>() {}.getType();
        arrayList = gson.fromJson(json,type);

        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }

        notes_adapter noteAdapter = new notes_adapter(this, R.layout.list_rows, arrayList);
        lvNotes.setAdapter(noteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //write code for menu options
    switch (item.getItemId()) {

                case R.id.add_notes:
                    Intent intent1 = new Intent(this, Add_note_activity.class);

                    Bundle bundle1 = new Bundle();
                    bundle1.putParcelableArrayList("arrayList", arrayList);
                    intent1.putExtras(bundle1);

                    startActivity(intent1);
                    return true;
                case R.id.delete_note:
                    Intent intent2 = new Intent(this, Delete_note_activity.class);

                    Bundle bundle2 = new Bundle();
                    bundle2.putParcelableArrayList("arrayList", arrayList);
                    intent2.putExtras(bundle2);

                    startActivity(intent2);
                    return true;


                default:
                    return super.onOptionsItemSelected(item);
            }
    }
}