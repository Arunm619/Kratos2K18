package com.kratos18.kratos2k18;

import android.content.SharedPreferences;
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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Winners");

        eventname = pref.getString("selectedevent", null);
        tv_eventname = findViewById(R.id.tvtvevent);
        tv_eventname.setText(eventname);
        btn_submitwinners = findViewById(R.id.btn_submitwinners);
        et_w1 = findViewById(R.id.et_typewinner1);
        et_w2 = findViewById(R.id.et_typewinner2);
        et_w3 = findViewById(R.id.et_typewinner3);


        btn_submitwinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRef.child(getString(R.string.paper_presentation)).child("Winner1").setValue("Kr-" );
                myRef.child(getString(R.string.paper_presentation)).child("Winner2").setValue("Kr-" );
                myRef.child(getString(R.string.paper_presentation)).child("Winner3").setValue("Kr-" );


                myRef.child(getString(R.string.project)).child("Winner1").setValue("Kr-" );
                myRef.child(getString(R.string.project)).child("Winner2").setValue("Kr-" );
                myRef.child(getString(R.string.project)).child("Winner3").setValue("Kr-" );


                String winner1 = et_w1.getText().toString(), winner2 = et_w2.getText().toString(), winner3 = et_w3.getText().toString();
                if (TextUtils.isEmpty(winner1)
                        ||
                        TextUtils.isEmpty(winner2) ||
                        TextUtils.isEmpty(winner3))

                {
                    Toast.makeText(ChooseWinners.this, "Enter all three.  ", Toast.LENGTH_SHORT).show();


                } else {
                    switch (eventname) {
                        case "Connections":
                            myRef.child(getString(R.string.connections)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.connections)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.connections)).child("Winner3").setValue("Kr-" + winner3);
                            break;
                        case "Code wars":
                            myRef.child(getString(R.string.codears)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.codears)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.codears)).child("Winner3").setValue("Kr-" + winner3);
                            break;
                        case "Trespassers":
                            // myRef.child(getString(R.string.trespassers)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.trespassers)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.trespassers)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.trespassers)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                        case "Google it":
                            // myRef.child(getString(R.string.Google_it)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.Google_it)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.Google_it)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.Google_it)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                        case "Homicide":
                            // myRef.child(getString(R.string.homicie)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.homicie)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.homicie)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.homicie)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                        case "Prison break":
                            // myRef.child(getString(R.string.prison)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.prison)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.prison)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.prison)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                        case "Maathi yosi":
                            //myRef.child(getString(R.string.mathi)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.mathi)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.mathi)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.mathi)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                        case "Midcity madness":
                            //myRef.child(getString(R.string.midcity)).child(student.getUUID()).setValue(student);
                            myRef.child(getString(R.string.midcity)).child("Winner1").setValue("Kr-" + winner1);
                            myRef.child(getString(R.string.midcity)).child("Winner2").setValue("Kr-" + winner2);
                            myRef.child(getString(R.string.midcity)).child("Winner3").setValue("Kr-" + winner3);

                            break;
                    }
                    Toast.makeText(ChooseWinners.this, "Winners successfully.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
