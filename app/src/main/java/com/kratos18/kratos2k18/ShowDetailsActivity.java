package com.kratos18.kratos2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class ShowDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_food, tv_phone, tv_UUID, tv_clgname, tv_deptname, tv_eventname;
    Button btn_participate;
    FirebaseDatabase database;
    DatabaseReference myRef,homeref;
    String event;
    RelativeLayout rl_showdetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        Gson gson = new Gson();
        String studentDataObjectAsAString = getIntent().getStringExtra("MyStudentObjectAsString");
        final Student student = gson.fromJson(studentDataObjectAsAString, Student.class);
        rl_showdetails=findViewById(R.id.rl_showdetails);
        tv_eventname = findViewById(R.id.tv_eventname);
        tv_clgname = findViewById(R.id.tv_clgname);
        tv_deptname = findViewById(R.id.tv_deptname);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phonenumber);
        tv_UUID = findViewById(R.id.tv_UUID);
        tv_food = findViewById(R.id.tv_food);
        btn_participate = findViewById(R.id.btn_participate);
        event = pref.getString("selectedevent", null);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Events");
        homeref=database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");

        tv_UUID.setText(student.getUUID());
        tv_clgname.setText(student.getCollegename());
        tv_phone.setText(String.valueOf(student.getTextphone()));
        tv_name.setText(student.getTextname());
        tv_deptname.setText(student.getDept());
        tv_food.setText(String.valueOf(student.isAte()));

        tv_eventname.setText(event);
        btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (event) {
                    case "Connections":
                        myRef.child(getString(R.string.connections)).child(student.getUUID()).setValue(student);
                        homeref.child(student.getUUID()).child("participatedevents").setValue("Connections");
                        break;
                    case "Code wars":
                        myRef.child(getString(R.string.codears)).child(student.getUUID()).setValue(student);
                        break;
                    case "Trespassers":
                        myRef.child(getString(R.string.trespassers)).child(student.getUUID()).setValue(student);
                        break;
                    case "Google it":
                        myRef.child(getString(R.string.Google_it)).child(student.getUUID()).setValue(student);
                        break;
                    case "Homicide":
                        myRef.child(getString(R.string.homicie)).child(student.getUUID()).setValue(student);
                        break;
                    case "Prison Break":
                        myRef.child(getString(R.string.prison)).child(student.getUUID()).setValue(student);
                        break;

                    case "Maathi Yosi":
                        myRef.child(getString(R.string.mathi)).child(student.getUUID()).setValue(student);
                        break;
                    case "Midcity Madness":
                        myRef.child(getString(R.string.midcity)).child(student.getUUID()).setValue(student);
                        break;
                }
                Snackbar.make(rl_showdetails, "Awesome! Succesfully Added...", Snackbar.LENGTH_INDEFINITE).setAction("Okay", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     startActivity(new Intent(ShowDetailsActivity.this,ScanQRActivity.class));
                     finish();


                    }
                }).show();
            }
        });
    }
}
