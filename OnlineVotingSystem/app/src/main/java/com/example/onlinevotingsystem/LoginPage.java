package com.example.onlinevotingsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    private RadioButton rb1,rb2;
    private EditText et1,et2;
    private Button bt1,bt2;
    private CheckBox cb1;
    private SQLiteDatabase db;
    private boolean isVoted; // Correct the spelling

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        // Initialize UI components
        rb1 = findViewById(R.id.vote);
        rb2 = findViewById(R.id.results);
        et1 = findViewById(R.id.voterid);
        et2 = findViewById(R.id.password);
        bt1 = findViewById(R.id.login);
        bt2 = findViewById(R.id.back);
        cb1 = findViewById(R.id.showpassword);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb1.isChecked()) {
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()) rb2.setChecked(false);
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb2.isChecked()) rb1.setChecked(false);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                Intent intent = new Intent(LoginPage.this, RegistrationPage.class);
                startActivity(intent);
                finish();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String voterid = et1.getText().toString().trim();
                String password = et2.getText().toString().trim();
                StringBuilder fillmessage = new StringBuilder();

                if (voterid.isEmpty()) fillmessage.append("\nVoter ID is required");
                if (password.isEmpty()) fillmessage.append("\nPassword is Required");

                if (fillmessage.length() == 0) {
                    if (isCredentialsValid(voterid, password)) {
                        Intent intent;
                        if (rb1.isChecked()) {
                            if (!isVoted()) {
                                intent = new Intent(LoginPage.this, VoterPage.class);
                                intent.putExtra("voterid", voterid);
                                startActivity(intent);
                            } else {
                                showAlert(view, "Voted", "You have already taken a part");
                            }
                        } else if (rb2.isChecked()) {
                            if (isVoted()) {
                                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                long startTime = preferences.getLong("startTime", 0);

                                long currentTime = System.currentTimeMillis();
                                long timePassed = currentTime - startTime;
                                long twentyFourHours = 24 * 60 * 60 * 1000;
                                if (timePassed < twentyFourHours) {
                                    new AlertDialog.Builder(LoginPage.this)
                                            .setTitle("Access Restricted")
                                            .setMessage("You can access the results page after 24 hours.")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();

                                } else {
                                   intent = new Intent(LoginPage.this, ResultPage.class);
                                    startActivity(intent);
                                }

                            } else {
                                showAlert(view, "Not Voted", "You have not participated");
                            }
                        } else {
                            showAlert(view, "Empty Blank", "Please Select an Option");
                        }
                    } else {
                        showAlert(view, "Credentials", "Password or VoterID is Incorrect");
                    }
                } else {
                    showAlert(view, "Empty Blank", "Please Select an Option" + fillmessage.toString());
                }
                clear();
            }
        });
    }

    private boolean isVoted() {
        return isVoted;
    }

    private boolean isCredentialsValid(String voterid, String password) {
        db = openOrCreateDatabase("Voter_Records", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Voters(name varchar,voterid varchar,mobileno varchar,password varchar,submitted varchar)");
        Cursor cursor = db.rawQuery("SELECT * FROM Voters WHERE voterid = ? AND password = ?", new String[]{voterid, password});

        boolean isValid = cursor.moveToFirst();
        if (isValid) {
            cursor = db.rawQuery("SELECT submitted FROM Voters WHERE voterid = ?", new String[]{voterid});
            if (cursor.moveToFirst()) {
                String submittedStatus = cursor.getString(0);
                isVoted = "Yes".equalsIgnoreCase(submittedStatus);
            }
        }
        cursor.close(); // Close the cursor after use
        return isValid;
    }

    private void showAlert(View view, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clear() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        et1.setText("");
        et2.setText("");
    }

}