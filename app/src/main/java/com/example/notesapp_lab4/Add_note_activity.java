package com.example.notesapp_lab4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Add_note_activity extends AppCompatActivity {

    EditText edAddNoteName;
    EditText edAddNoteText;
    ArrayList<notes> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.edAddNoteName = findViewById(R.id.edNoteName);
        this.edAddNoteText = findViewById(R.id.edNoteDesc);

        Bundle bundle = getIntent().getExtras();
        arrayList = bundle.getParcelableArrayList("arrayList");
    }

    public void onBtnAddClick(View view) {
        String noteNameToAdd = this.edAddNoteName.getText().toString();
        String noteTextToAdd = this.edAddNoteText.getText().toString();

        if (noteNameToAdd.equals("") || noteTextToAdd.equals("")) {
            Snackbar.make(view, "notes name or text field is empty!", Snackbar.LENGTH_SHORT).show();
        }
        else {
            arrayList.add(new notes(noteNameToAdd, noteTextToAdd));

            SharedPreferences sharedPref = getSharedPreferences(constants.NOTES_FILE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            Gson gson = new Gson();
            String json = gson.toJson(arrayList);

            editor.putString(constants.NOTE_LIST_KEY, json);
            editor.apply();
            finish();
            Log.e("onBtnAddClick", "Note successfully added!");
        }
    }
}