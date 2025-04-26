package com.example.onlinevotingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultPage extends AppCompatActivity {
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv01,tv02,tv03,tv04,tv05,tv06;
    private Button exit;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.resultpage);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv01 = findViewById(R.id.tv01);
        tv02 = findViewById(R.id.tv02);
        tv03 = findViewById(R.id.tv03);
        tv04 = findViewById(R.id.tv04);
        tv05 = findViewById(R.id.tv05);
        tv06 = findViewById(R.id.tv06);
        exit = findViewById(R.id.exit);
        db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Candidates(id varchar,candidate_name varchar,party_name varchar,votes varchar)");
        Cursor cursor = db.rawQuery("SELECT party_name,votes FROM Candidates", new String[]{});
        int index = 1; // Start index for TextView
        while (cursor.moveToNext() && index <= 6) {
            String partyname = cursor.getString(0);
            String votes = cursor.getString(1);
            switch (index) {
                case 1:
                    tv1.setText(votes);
                    tv01.setText(partyname);
                    break;
                case 2:
                    tv2.setText(votes);
                    tv02.setText(partyname);
                    break;
                case 3:
                    tv3.setText( votes);
                    tv03.setText(partyname);
                    break;
                case 4:
                    tv4.setText( votes);
                    tv04.setText(partyname);
                    break;
                case 5:
                    tv5.setText( votes);
                    tv05.setText(partyname);
                    break;
                case 6:
                    tv6.setText( votes);
                    tv06.setText(partyname);
                    break;
            }
            index++;
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}