package com.kratos18.kratos2k18;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseWinners extends AppCompatActivity {

    TextView tv_eventname;
    Button btn_submitwinners;
    EditText et_w1, et_w2, et_w3;
    String eventname;
    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_winners);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Winners");
        isOnline();
        eventname = pref.getString("selectedevent", null);
        tv_eventname = findViewById(R.id.tvtvevent);
        tv_eventname.setText(eventname);
        btn_submitwinners = findViewById(R.id.btn_submitwinners);
        et_w1 = findViewById(R.id.et_typewinner1);
        et_w2 = findViewById(R.id.et_typewinner2);
        et_w3 = findViewById(R.id.et_typewinner3);

      /*  myRef.child(getString(R.string.paper_presentation)).child("Winner1").setValue("KR-");
        myRef.child(getString(R.string.paper_presentation)).child("Winner2").setValue("KR-");
        myRef.child(getString(R.string.paper_presentation)).child("Winner3").setValue("KR-");


        myRef.child(getString(R.string.project)).child("Winner1").setValue("KR-");
        myRef.child(getString(R.string.project)).child("Winner2").setValue("KR-");
        myRef.child(getString(R.string.project)).child("Winner3").setValue("KR-");

   */
        btn_submitwinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String winner1 = et_w1.getText().toString(), winner2 = et_w2.getText().toString(), winner3 = et_w3.getText().toString();

                if (TextUtils.isEmpty(winner1) || TextUtils.isEmpty(winner2) ||
                        TextUtils.isEmpty(winner3)) {
                    Toast.makeText(ChooseWinners.this, "Fill all the fields.  ", Toast.LENGTH_SHORT).show();
                } else if (winner1.length() != 10)
                    Toast.makeText(ChooseWinners.this, "Check winner 1", Toast.LENGTH_SHORT).show();
                else if (winner2.length() != 10)
                    Toast.makeText(ChooseWinners.this, "Check winner 2", Toast.LENGTH_SHORT).show();
                else if (winner3.length() != 10)
                    Toast.makeText(ChooseWinners.this, "Check winner 3", Toast.LENGTH_SHORT).show();
            /*   if (isvalid(winner1)  )
                   Toast.makeText(ChooseWinners.this, "Invalid Numbers", Toast.LENGTH_SHORT).show();
            */

                else {
                    switch (eventname) {
                        case "Paper Presentation":
                            myRef.child(getString(R.string.paper_presentation)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.paper_presentation)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.paper_presentation)).child("Winner3").setValue("KR-" + winner3);
                            break;

                        case "Clash of Codes":
                            myRef.child(getString(R.string.clash_of_codes)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.clash_of_codes)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.clash_of_codes)).child("Winner3").setValue("KR-" + winner3);
                            break;

                        case "Tech Treasure Hunt":
                            myRef.child(getString(R.string.tech_treasure_hunt)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.tech_treasure_hunt)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.tech_treasure_hunt)).child("Winner3").setValue("KR-" + winner3);

                            break;
                        case "Dead Locked DB":
                            myRef.child(getString(R.string.dead_locked_db)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.dead_locked_db)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.dead_locked_db)).child("Winner3").setValue("KR-" + winner3);
                            break;

                        case "Google it":
                            myRef.child(getString(R.string.google_it)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.google_it)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.google_it)).child("Winner3").setValue("KR-" + winner3);
                            break;
                        case "Gadgets and Gizmos":
                            myRef.child(getString(R.string.gadgets_and_gizmos)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.gadgets_and_gizmos)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.gadgets_and_gizmos)).child("Winner3").setValue("KR-" + winner3);

                            break;

                        //non tech


                        case "Murder in Multiplayer":
                            myRef.child(getString(R.string.murder_in_multiplayer)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.murder_in_multiplayer)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.murder_in_multiplayer)).child("Winner3").setValue("KR-" + winner3);

                            break;

                        case "Prison Break":
                            myRef.child(getString(R.string.prison)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.prison)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.prison)).child("Winner3").setValue("KR-" + winner3);

                            break;


                        case "Pitch Impossible":
                            myRef.child(getString(R.string.pitch_impossible)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.pitch_impossible)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.pitch_impossible)).child("Winner3").setValue("KR-" + winner3);

                            break;
                        case "Comic Quiz":
                            myRef.child(getString(R.string.comic_quiz)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.comic_quiz)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.comic_quiz)).child("Winner3").setValue("KR-" + winner3);

                            break;

                        case "Box Cricket-Futsal":
                            myRef.child(getString(R.string.box_cricket_futsal)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.box_cricket_futsal)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.box_cricket_futsal)).child("Winner3").setValue("KR-" + winner3);

                            break;

                        case "Gaming":
                            myRef.child(getString(R.string.gaming)).child("Winner1").setValue("KR-" + winner1);
                            myRef.child(getString(R.string.gaming)).child("Winner2").setValue("KR-" + winner2);
                            myRef.child(getString(R.string.gaming)).child("Winner3").setValue("KR-" + winner3);

                            break;
                    }


                    Toast.makeText(ChooseWinners.this, "Winners added successfully for " + eventname, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (manager != null) {
            info = manager.getActiveNetworkInfo();
        }

        if (info == null) {
            new AlertDialog.Builder(ChooseWinners.this)
                    .setTitle(getResources().getString(R.string.internet_error))
                    .setMessage(getResources().getString(R.string.internet_error_long)).setCancelable(false)
                    .setNeutralButton("Reload", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            isOnline();
                        }

                    })
                    .show();

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
