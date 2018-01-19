package com.kratos18.kratos2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterNew extends AppCompatActivity {
    EditText et_name, et_collegename, et_phone, et_email, et_deptname;
    String name, clgname, email, deptname, UniqueUserID;
    Long phonenumber;
    Button btn_register;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);

        et_name = findViewById(R.id.input_name);
        et_collegename = findViewById(R.id.input_collegename);
        et_phone = findViewById(R.id.input_phonenumber);
        et_email = findViewById(R.id.input_email);
        et_deptname = findViewById(R.id.input_deptname);

        btn_register = findViewById(R.id.btn_submit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isnullcheck();
                Student student = new Student();
                student.setTextname(name);
                student.setCollegename(clgname);
                student.setDept(deptname);
                student.setEmail42(email);
                student.setTextphone(phonenumber);
                student.setUUID("KR-" + phonenumber);
                student.setQrcode("Null");
                student.setAte(false);

                myRef.child(student.getUUID()).setValue(student);

                Toast.makeText(RegisterNew.this, "Awesome! Succesfully registered...", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void isnullcheck() {

        name = et_name.getText().toString().trim();
        clgname = et_collegename.getText().toString().trim();
        phonenumber = Long.valueOf(et_phone.getText().toString().trim());
        email = et_email.getText().toString().trim();
        deptname = et_deptname.getText().toString().trim();
        //boolean a = false;
        if (TextUtils.isEmpty(name))
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();

        else if (TextUtils.isEmpty(clgname))
            Toast.makeText(this, "Enter College Name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isDigitsOnly(String.valueOf(phonenumber)))
            Toast.makeText(this, "Enter Phone number", Toast.LENGTH_SHORT).show();

        else if (TextUtils.isEmpty(deptname))
            Toast.makeText(this, "Enter dept name", Toast.LENGTH_SHORT).show();


    }
}
