package com.kratos18.kratos2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQRActivity extends AppCompatActivity {
    Button btn_scanqr,btn_addtoevent;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView tv_eventname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        btn_scanqr = findViewById(R.id.btn_scanqr);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        btn_addtoevent=findViewById(R.id.btn_addtoevent);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        tv_eventname= findViewById(R.id.tv_eventname);
        tv_eventname.setText(pref.getString("selectedevent", null)); // getting Event name



        btn_addtoevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               checkqr();

            }
        });

        btn_scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkqr();
            }
        });
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
//                                if (student != null) {
//                                    Toast.makeText(ScanQRActivity.this, "Student name"+student.getQrcode(), Toast.LENGTH_SHORT).show();
//                                }

                                if (student != null && student.getQrcode().equals(result.getContents())) {
                                    Toast.makeText(ScanQRActivity.this, "Found...Pls wait" + result.getContents(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ScanQRActivity.this, ShowDetailsActivity.class);
                                    Gson gson = new Gson();
                                    String studentDataObjectAsAString = gson.toJson(student);
                                    intent.putExtra("MyStudentObjectAsString", studentDataObjectAsAString);

                                    startActivity(intent);
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });


//            Toast.makeText(this, ""+result.getContents(), Toast.LENGTH_SHORT).show();
//
//            myRef.child(result.getContents()).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
////                    if (dataSnapshot.exists()) {
////                        Toast.makeText(ScanQRActivity.this, "V="+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
////
////                    } else
////                        Toast.makeText(ScanQRActivity.this, "No"+dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
////                }
//
//                    Toast.makeText(ScanQRActivity.this, "Value"+dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
//
//
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });




        } else

        {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();


        }
    }
}



/*
*
            Query query = myRef.child("Kr-123")
                    .child("Qrcode")
                    .equalTo(result.getContents());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                        Student student = singleSnapshot.getValue(Student.class);
                        Intent intent = new Intent(ScanQRActivity.this, ShowDetailsActivity.class);
                        intent.putExtra("Studentobj", (Parcelable) student);
                        startActivity(intent);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

*
* */