package com.kratos18.kratos2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MarkQRActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    String QRCODEValue;
    Button btn_scan;
    String userid;
    EditText et_uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_qr);

        et_uuid = findViewById(R.id.input_uuid);

        btn_scan = findViewById(R.id.btn_setqrcode);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userid = et_uuid.getText().toString();
                if (userid != null)
                    checkqr();
                else Toast.makeText(MarkQRActivity.this, "Enter number", Toast.LENGTH_SHORT).show();
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");


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
            Toast.makeText(this, "Successfully set ", Toast.LENGTH_SHORT).show();


        } else

        {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();


        }
    }
}

