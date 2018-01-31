package com.kratos18.kratos2k18;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    ImageView img_lion;
    RelativeLayout rl_reg;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new);
        rl_reg = findViewById(R.id.rl_reg);
        img_lion = findViewById(R.id.img_lion);
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
        isOnline();

        btn_validatenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int a = isnullcheck();
                if (a == -1) {
                    return;
                }
                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = null;
                if (manager != null) {
                    info = manager.getActiveNetworkInfo();
                }

                if (info != null)

                isvalid();
                else
                    Toast.makeText(RegisterNew.this, "Check Internet. Failed to add.", Toast.LENGTH_SHORT).show();


            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = null;
                if (manager != null) {
                    info = manager.getActiveNetworkInfo();
                }

                if (info != null)
                    doit();
                else
                    Toast.makeText(RegisterNew.this, "Check Internet. Failed to add.", Toast.LENGTH_SHORT).show();

            }
        });

        img_lion.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Snackbar.make(rl_reg, "App developed By Arunm619", Snackbar.LENGTH_SHORT).setAction("Check Github!", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        visitwebsite();
                    }
                }).show();
                return false;
            }
        });


    }

    private void doit() {

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

        Snackbar.make(rl_reg, "Success " + name + " :)", Snackbar.LENGTH_INDEFINITE).setAction("Okay", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearall();
            }
        }).show();


    }

    private void visitwebsite() {
        String url = "https://github.com/Arunm619";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.registermenu, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.callarun) {
            new AlertDialog.Builder(RegisterNew.this)
                    .setTitle(getResources().getString(R.string.help_register))
                    .setMessage(getResources().getString(R.string.surehelp)).setCancelable(false)
                    .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            callanumber("9940245619");

                        }

                    })
                    .setNegativeButton("Nope", null)

                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callanumber(String aphonenumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + aphonenumber));
        startActivity(intent);
    }


    private void isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (manager != null) {
            info = manager.getActiveNetworkInfo();
        }

        if (info == null) {
            new AlertDialog.Builder(RegisterNew.this)
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

}
