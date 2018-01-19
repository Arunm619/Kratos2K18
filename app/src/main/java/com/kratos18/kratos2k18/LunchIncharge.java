package com.kratos18.kratos2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class LunchIncharge extends AppCompatActivity {
    TextView tv_name, tv_food, tv_phone, tv_UUID, tv_clgname, tv_deptname;
    Button btn_foodsupplied;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_incharge);
        Gson gson = new Gson();
        String studentDataObjectAsAString = getIntent().getStringExtra("MyStudentObjectAsString");
        final Student student = gson.fromJson(studentDataObjectAsAString, Student.class);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");

        tv_clgname = findViewById(R.id.tv_clgname);
        tv_deptname = findViewById(R.id.tv_deptname);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phonenumber);
        tv_UUID = findViewById(R.id.tv_UUID);
        tv_food = findViewById(R.id.tv_food);
        btn_foodsupplied = findViewById(R.id.btn_foodsupplied);

        tv_UUID.setText(student.getUUID());
        tv_clgname.setText(student.getCollegename());
        tv_phone.setText(String.valueOf(student.getTextphone()));
        tv_name.setText(student.getTextname());
        tv_deptname.setText(student.getDept());
        tv_food.setText(String.valueOf(student.isAte()));


        btn_foodsupplied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                myRef.child(student.getUUID()).child("ate").setValue(true);

            }
        });
    }


}

