package com.example.onlinevotingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Add extends AppCompatActivity {

    private SQLiteDatabase db;
    private Button bt1, bt2;
    private EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassword);

        et1 = (EditText) findViewById(R.id.name);
        et2 = (EditText) findViewById(R.id.name1);
        bt1 = (Button) findViewById(R.id.submit);
        bt2 = (Button) findViewById(R.id.back);

        // Step 1: Initialize Database and Table
        db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.rawQuery("SELECT * FROM Voters", null);

                if (cursor.getCount() > 0) {
                    StringBuilder builder = new StringBuilder();

                    while (cursor.moveToNext()) {
                        String id = cursor.getString(0);  // Access the 'id' column
                        String candidateName = cursor.getString(1);  // Access the 'candidate_name' column
                        String partyName = cursor.getString(2);  // Access the 'party_name' column
                        String votes = cursor.getString(3);
                        String s=cursor.getString(4);// Access the 'votes' column

                        builder.append("ID: ").append(id)
                                .append("\nCandidate: ").append(candidateName)
                                .append("\nParty: ").append(partyName)
                                .append("\nVotes: ").append(votes).append("\nvoted: ").append(s)
                                .append("\n\n");
                    }

                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("Candidates List");
                    dialog.setMessage(builder.toString());
                    dialog.setPositiveButton("OK", null);
                    dialog.show();
                }

                cursor.close(); // Close the cursor to free up resources
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.rawQuery("SELECT * FROM Candidates", null);

                if (cursor.getCount() > 0) {
                    StringBuilder builder = new StringBuilder();

                    while (cursor.moveToNext()) {
                        String id = cursor.getString(0);  // Access the 'id' column
                        String candidateName = cursor.getString(1);  // Access the 'candidate_name' column
                        String partyName = cursor.getString(2);  // Access the 'party_name' column
                        String votes = cursor.getString(3);  // Access the 'votes' column

                        builder.append("ID: ").append(id)
                                .append("\nCandidate: ").append(candidateName)
                                .append("\nParty: ").append(partyName)
                                .append("\nVotes: ").append(votes)
                                .append("\n\n");
                    }

                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("Candidates List");
                    dialog.setMessage(builder.toString());
                    dialog.setPositiveButton("OK", null);
                    dialog.show();
                }

                cursor.close(); // Close the cursor to free up resources
            }
        });
    }
}
