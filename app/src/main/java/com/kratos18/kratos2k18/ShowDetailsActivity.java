package com.kratos18.kratos2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class ShowDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_phone, tv_UUID, tv_clgname, tv_deptname;
    Button btn_participate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        Gson gson = new Gson();
        String studentDataObjectAsAString = getIntent().getStringExtra("MyStudentObjectAsString");
        Student student = gson.fromJson(studentDataObjectAsAString, Student.class);

        tv_clgname = findViewById(R.id.tv_clgname);
        tv_deptname = findViewById(R.id.tv_deptname);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phonenumber);
        tv_UUID = findViewById(R.id.tv_UUID);

        btn_participate = findViewById(R.id.btn_participate);

        tv_UUID.setText(student.getUUID());
        tv_clgname.setText(student.getCollegename());
        tv_phone.setText(String.valueOf(student.getTextphone()));
        tv_name.setText(student.getTextname());
        tv_deptname.setText(student.getDept());


        btn_participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set that uuid to the events...
            }
        });
    }
}
