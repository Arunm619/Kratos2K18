package com.kratos18.kratos2k18;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterNew extends AppCompatActivity {
    EditText et_name, et_collegename, et_phone, et_email, et_deptname;
    String name, clgname, email, deptname, UniqueUserID;
    String phonenumber;
    Button btn_register, btn_validatenumber;

    FirebaseDatabase database;
    DatabaseReference myRef;

    RelativeLayout rl_reg;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        rl_reg = findViewById(R.id.rl_reg);

        et_name = findViewById(R.id.input_name);
        et_collegename = findViewById(R.id.input_collegename);
        et_phone = findViewById(R.id.input_phonenumber);
        et_email = findViewById(R.id.input_email);
        et_deptname = findViewById(R.id.input_deptname);

        btn_register = findViewById(R.id.btn_submit);
        btn_validatenumber = findViewById(R.id.btn_validatenumber);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        // ListRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/AddedNumbers");

        btn_register.setEnabled(false);

        btn_validatenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = isnullcheck();
                if (a == -1) {
                    return;
                }
                isvalid();

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student = new Student();
                student.setTextname(name);
                student.setCollegename(clgname);
                student.setDept(deptname);
                student.setEmail(email);
                student.setTextphone(Long.valueOf(phonenumber));
                student.setUUID("KR-" + phonenumber);
                student.setQrcode("Null");
                student.setParticipatedevents(" ");
                student.setAte(false);

                myRef.child(student.getUUID()).setValue(student);

                //pollRef.child("comments").push();
                //commentRef.setValue(comment);

//                    Toast.makeText(RegisterNew.this, "Awesome! Succesfully registered...", Toast.LENGTH_SHORT).show();

                Snackbar.make(rl_reg, "Awesome! Succesfully registered...", Snackbar.LENGTH_INDEFINITE).setAction("Okay", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearall();
                    }
                }).show();
            }
        });


    }

    private void isvalid() {


        myRef.child("KR-" + phonenumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(RegisterNew.this, "Already Number exixts", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(RegisterNew.this, "okay all cool", Toast.LENGTH_SHORT).show();
                    btn_register.setEnabled(true);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void clearall() {
        et_name.setText("");
        et_deptname.setText("");
        et_email.setText("");
        et_collegename.setText("");
        et_phone.setText("");
        btn_register.setEnabled(false);

    }

    private int isnullcheck() {

        name = et_name.getText().toString().trim();
        clgname = et_collegename.getText().toString().trim();
        phonenumber = et_phone.getText().toString().trim();
        email = et_email.getText().toString().trim();
        deptname = et_deptname.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return -1;
        } else if (TextUtils.isEmpty(clgname)) {
            Toast.makeText(this, "Enter College Name", Toast.LENGTH_SHORT).show();
            return -1;
        } else if (phonenumber.length() != 10) {
            Toast.makeText(this, "Phone number should be of 10 digits.", Toast.LENGTH_SHORT).show();
            return -1;
        } else if (TextUtils.isEmpty(String.valueOf(phonenumber))) {
            Toast.makeText(this, "Enter Phone number", Toast.LENGTH_SHORT).show();
            return -1;
        } else if (TextUtils.isEmpty(deptname)) {
            Toast.makeText(this, "Enter dept name", Toast.LENGTH_SHORT).show();
            return -1;
        }


        return 0;
    }
}
