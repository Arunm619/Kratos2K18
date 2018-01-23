package com.kratos18.kratos2k18;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class ShowDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_food, tv_phone, tv_UUID, tv_clgname, tv_deptname, tv_eventname;
    Button btn_participate;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        Gson gson = new Gson();
        String studentDataObjectAsAString = getIntent().getStringExtra("MyStudentObjectAsString");
        final Student student = gson.fromJson(studentDataObjectAsAString, Student.class);
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
                    case "Prison break":
                        myRef.child(getString(R.string.prison)).child(student.getUUID()).setValue(student);
                        break;
                    case "Maathi yosi":
                        myRef.child(getString(R.string.mathi)).child(student.getUUID()).setValue(student);
                        break;
                    case "Midcity madness":
                        myRef.child(getString(R.string.midcity)).child(student.getUUID()).setValue(student);
                        break;
                }
                Toast.makeText(ShowDetailsActivity.this, "Added successfully.", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
