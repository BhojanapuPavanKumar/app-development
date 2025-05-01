package com.example.onlinevotingsystem;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistrationPage extends AppCompatActivity {
    SQLiteDatabase db;
    private EditText ed1,ed2,ed3,ed4;
    private Button bt1;
    private CheckedTextView ctv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registrationpage);
        String adminProvidedTime = "2024-08-31 10:00:00";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(adminProvidedTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            long startTime = date.getTime();
            SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("startTime", startTime);
            editor.apply();
        }


        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.voterid);
        ed3 = (EditText) findViewById(R.id.mobileno);
        ed4 = (EditText) findViewById(R.id.password);

        bt1 = (Button) findViewById(R.id.register);
        ctv1 = (CheckedTextView) findViewById(R.id.login);

        ctv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationPage.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString().trim();
                String voterid=ed2.getText().toString().trim();
                String mobileno=ed3.getText().toString().trim();
                String password=ed4.getText().toString().trim();

                StringBuilder fillmessage = new StringBuilder();

                if(name.isEmpty())
                    fillmessage.append("Name is Required\n");
                if(voterid.isEmpty())
                    fillmessage.append("Voter Id is Required\n");
                else {
                    try {
                        Integer.parseInt(voterid);
                    } catch (NumberFormatException e) {
                        fillmessage.append("Voter ID must be an integer.\n");
                    }
                }
                if(mobileno.isEmpty())
                    fillmessage.append("Mobile Number is Required\n");
                else if (!mobileno.matches("\\d{10}"))
                    fillmessage.append("Mobile number must be exactly 10 digits.\n");
                if(password.isEmpty())
                    fillmessage.append("Password is required");
                else if (!isStrongPassword(password))
                    fillmessage.append("Password must be strong (at least 8 characters, include uppercase, lowercase, digit, and special character).\n");
                if(fillmessage.length() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Empty Blanks").setMessage(fillmessage.toString()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS Voters(name varchar,voterid varchar,mobileno varchar,password varchar,submitted varchar)");

                    Cursor cursor = db.rawQuery("SELECT * FROM Voters WHERE voterid = ?", new String[]{voterid});

                    if (cursor.getCount() > 0) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Duplicate Voter ID").setMessage("The Voter ID already exists. Please use a different ID.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        db.execSQL("INSERT INTO Voters (name, voterid, mobileno, password, submitted) VALUES (?, ?, ?, ?, ?)",
                                new Object[]{name, voterid, mobileno, password, "NO"});
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("Registration Successful").setMessage("You have successfully registered.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    cursor.close();
                }
                clear();
            }
        });
    }
    private void clear(){
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
    }

    private boolean isStrongPassword(String password) {
            if (password.length() < 8) return false;
            boolean uppercase = false;
            boolean lowercase = false;
            boolean digit = false;
            boolean specialchar = false;
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) uppercase = true;
                else if (Character.isLowerCase(c)) lowercase = true;
                else if (Character.isDigit(c)) digit = true;
                else if (!Character.isLetterOrDigit(c))specialchar = true;
            }
            return uppercase && lowercase && digit && specialchar;
        }

}