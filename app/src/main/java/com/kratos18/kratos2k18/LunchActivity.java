package com.kratos18.kratos2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LunchActivity extends AppCompatActivity {
    Button btn_scanqr;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        btn_scanqr = findViewById(R.id.btn_scanqr);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");



        btn_scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkqr();
            }
        });
    }


    private void checkqr() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan:");
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
                                // Toast.makeText(ScanQRActivity.this, "Student name"+student.getTextname(), Toast.LENGTH_SHORT).show();

                                if (student.getQrcode().equals(result.getContents())) {
                                    Intent intent = new Intent(LunchActivity.this, LunchIncharge.class);
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

        } else

        {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();


        }
    }
}
