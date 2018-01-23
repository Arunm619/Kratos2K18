package com.kratos18.kratos2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkQRActivity extends AppCompatActivity {

    private static final String TAG ="Arun checks" ;
    FirebaseDatabase database;
    DatabaseReference myRef, ListRef;

    String QRCODEValue;
    Button btn_scan,btn_checkredundancy;
    String userid;
    EditText et_uuid;
    List<Object> listofnumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_qr);

        et_uuid = findViewById(R.id.input_uuid);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        ListRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/AddedNumbers");
        listofnumbers = new ArrayList<>();

//        ListRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, Object> td = (HashMap<String, Object>) dataSnapshot.getValue();
//
//
//                Collection<Object> values = td.values();
//                Toast.makeText(MarkQRActivity.this, "" + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        btn_scan = findViewById(R.id.btn_setqrcode);
        btn_scan.setEnabled(false);
        btn_checkredundancy=findViewById(R.id.btn_checkredundancy);
        //Toast.makeText(MarkQRActivity.this, "Size" + listofnumbers.size(), Toast.LENGTH_SHORT).show();


btn_checkredundancy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        userid = et_uuid.getText().toString();
        if (TextUtils.isEmpty(userid))
            Toast.makeText(MarkQRActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();
        else
            ispresentalready(userid);


    }
});

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = et_uuid.getText().toString();


                if (TextUtils.isEmpty(userid))
                    Toast.makeText(MarkQRActivity.this, "Enter a number", Toast.LENGTH_SHORT).show();


                else
                    checkqr();

            }
        });


    }


    private void ispresentalready(final String userid) {


        myRef.child("KR-"+userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(MarkQRActivity.this, "Found", Toast.LENGTH_SHORT).show();
                btn_scan.setEnabled(true);
                } else
                    Toast.makeText(MarkQRActivity.this, "No", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() != null) {
            QRCODEValue = result.getContents();
            myRef.child("KR-" + userid).child("Qrcode").setValue(QRCODEValue);

            Toast.makeText(MarkQRActivity.this, "Successfully set ", Toast.LENGTH_SHORT).show();


        } else

        {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();


        }
    }
}

