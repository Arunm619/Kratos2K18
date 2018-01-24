package com.kratos18.kratos2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Admin extends AppCompatActivity {


    Button btn_scanqrfullinfo, btn_submit;
    EditText Et_input_uuid;
    FirebaseDatabase database;
    DatabaseReference myRef, ListRef;
    String person;
    TextView tv_UUID;


    TextView tv_name, tv_clgname, tv_phone, tv_deptname, tv_participatedevents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tv_UUID = findViewById(R.id.tv_UUID);
        btn_scanqrfullinfo = findViewById(R.id.btn_scanqrfullinfo);
        Et_input_uuid = findViewById(R.id.input_uuid);
        tv_clgname = findViewById(R.id.tv_clgname);
        tv_deptname = findViewById(R.id.tv_deptname);
        tv_participatedevents = findViewById(R.id.tv_particapatedevents);
        tv_participatedevents.setText("Loading...Events");
        tv_phone = findViewById(R.id.tv_phonenumber);
        tv_name = findViewById(R.id.tv_name);

        btn_submit = findViewById(R.id.btn_submit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");


        btn_scanqrfullinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkqr();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person = "KR-" + Et_input_uuid.getText().toString();
                if (person.length() > 4) {

                    findtheuser();
                } else {
                    Toast.makeText(Admin.this, "Check Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void findevents(final Student student) {
        final StringBuffer events = new StringBuffer();
        DatabaseReference eref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Events");


        tv_participatedevents.setText(new String(events));

    }

    private void findtheuser() {
        myRef.child(person).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Student student = dataSnapshot.getValue(Student.class);
                //Toast.makeText(Admin.this, ""+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                setviews(student);
                //findevents(student);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setviews(Student student) {

        tv_name.setText(student.getTextname());
        tv_phone.setText(String.valueOf(student.getTextphone()));
        tv_deptname.setText(student.getDept());
        tv_clgname.setText(student.getCollegename());
        tv_UUID.setText(student.getUUID());
        tv_participatedevents.setText(student.getParticipatedevents());
    }


    private void checkqr() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan the qr");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() != null) {

//the value of qr code.

            FirebaseDatabase.getInstance().getReference().child("Users")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Student student = snapshot.getValue(Student.class);

                                if (student != null && student.getQrcode().equals(result.getContents())) {
                                    Toast.makeText(Admin.this, "Found...Pls wait" + result.getContents(), Toast.LENGTH_SHORT).show();
                                    setviews(student);
                                    findevents(student);

                                    break;
                                }


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });


        }
    }
}