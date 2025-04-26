package com.example.onlinevotingsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class VoterPage extends AppCompatActivity {
    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6;
    private Button bt1,bt2;
    private SQLiteDatabase db;
    private int c=0;
    private String voterid;
    private TextView tv01,tv02,tv03,tv04,tv05,tv06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.voterpage);
        db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);

        // Retrieve voterid from Intent
        voterid = getIntent().getStringExtra("voterid");

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        cb6 = findViewById(R.id.cb6);
        bt1 = findViewById(R.id.submit);
        bt2 = findViewById(R.id.exit);
        tv01 = findViewById(R.id.tv01);
        tv02 = findViewById(R.id.tv02);
        tv03 = findViewById(R.id.tv03);
        tv04 = findViewById(R.id.tv04);
        tv05 = findViewById(R.id.tv05);
        tv06 = findViewById(R.id.tv06);
        bt2 = findViewById(R.id.exit);

        db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Candidates(id varchar,candidate_name varchar,party_name varchar,votes varchar)");
        Cursor cursor = db.rawQuery("SELECT party_name FROM Candidates", new String[]{});
        int index = 1; // Start index for TextView
        while (cursor.moveToNext() && index <= 6) {
            String partyname = cursor.getString(0);

            switch (index) {
                case 1:
                    tv01.setText(partyname);
                    break;
                case 2:

                    tv02.setText(partyname);
                    break;
                case 3:
                    tv03.setText(partyname);
                    break;
                case 4:
                    tv04.setText(partyname);
                    break;
                case 5:
                    tv05.setText(partyname);
                    break;
                case 6:
                    tv06.setText(partyname);
                    break;
            }
            index++;
        }
        cursor.close();

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked()) {
                    clear(1);
                    c = 1;
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb2.isChecked()) {
                    clear(2);
                    c = 2;
                }
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb3.isChecked()){
                    clear(3);
                    c=3;
                }}
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb4.isChecked()){
                    clear(4);
                    c=4;
                }}
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb5.isChecked()){
                    clear(5);
                    c=5;
                }}
        });
        cb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb6.isChecked()){
                    clear(6);
                    c=6;
                }}
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c > 0) {
                    if (isvote(view)) {
                        db.execSQL("CREATE TABLE IF NOT EXISTS Voters(name varchar, voterid varchar, mobileno varchar, password varchar, submitted varchar)");
                        db.execSQL("UPDATE Voters SET submitted = ? WHERE voterid = ?", new String[]{"Yes", voterid});
                            new AlertDialog.Builder(view.getContext()).setTitle("Voted")
                                    .setMessage("You have successfully participated")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(VoterPage.this, LoginPage.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish(); // O
                                        }
                                    }).show();
                    }
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("Vote");
                    dialog.setMessage("Please Select any Candidate");
                    dialog.setPositiveButton("OK", null);
                    dialog.show();
                }
                clear(0);  // Clear all checkboxes after submission
            }
        });
    }


    private boolean isvote(View view){
        db.execSQL("CREATE TABLE IF NOT EXISTS Candidates(id varchar,candidate_name varchar,party_name varchar,votes varchar)");
        Cursor cursor = db.rawQuery("SELECT votes FROM Candidates WHERE id = ?", new String[]{""+c});
        if (cursor.moveToFirst()) {
            int currentVotes = cursor.getInt(0);
            int newVotes = currentVotes + 1;
            db.execSQL("UPDATE Candidates SET votes = ? WHERE id = ?", new String[]{String.valueOf(newVotes),""+c});
            AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
            dialog.setTitle("Vote Updated");
            dialog.setMessage("Candidate ID: " + c + "\nNew Votes: " + newVotes);
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }
        cursor.close(); // Close the cursor to free up resources
        return true;
    }


    private void clear(int m){
        if(m!=1)
        cb1.setChecked(false);
        if(m!=2)
        cb2.setChecked(false);
        if(m!=3)
        cb3.setChecked(false);
        if(m!=4)
        cb4.setChecked(false);
        if(m!=5)
        cb5.setChecked(false);
        if(m!=6)
        cb6.setChecked(false);
    }
}