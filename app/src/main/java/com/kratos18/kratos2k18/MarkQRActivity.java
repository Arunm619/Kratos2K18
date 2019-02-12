package com.kratos18.kratos2k18;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

    private static final String TAG = "Arun checks";
    FirebaseDatabase database;
    DatabaseReference myRef, ListRef;

    String QRCODEValue;
    Button btn_scan, btn_checkredundancy;
    String userid;
    EditText et_uuid;
    RelativeLayout rl_marqr;
    ImageView img_lion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_qr);
        rl_marqr = findViewById(R.id.rl_marqr);
        et_uuid = findViewById(R.id.input_uuid);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");

        img_lion = findViewById(R.id.img_lion);

        btn_scan = findViewById(R.id.btn_setqrcode);
        btn_scan.setEnabled(false);
        btn_checkredundancy = findViewById(R.id.btn_checkredundancy);
        //Toast.makeText(MarkQRActivity.this, "Size" + listofnumbers.size(), Toast.LENGTH_SHORT).show();
        isOnline();

        btn_checkredundancy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(et_uuid.getWindowToken(), 0);

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = null;
                if (manager != null) {
                    info = manager.getActiveNetworkInfo();
                }
                if (info!=null)


                {
                    userid = et_uuid.getText().toString();
                    if (TextUtils.isEmpty(userid))
                        Snackbar.make(rl_marqr, "Please Enter a Number!", Snackbar.LENGTH_SHORT).
                                show();

                    else if (userid.length() != 10) {
                        Snackbar.make(rl_marqr, "Please Check the Number!", Snackbar.LENGTH_SHORT).
                                show();

                    } else
                        ispresentalready(userid);
                }
                else
                    Toast.makeText(MarkQRActivity.this, "Please Check Internet Connection!", Toast.LENGTH_SHORT).show();


            }
        });

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*userid = et_uuid.getText().toString();

                if (TextUtils.isEmpty(userid))
                    Snackbar.make(rl_marqr, "Enter Number...", Snackbar.LENGTH_SHORT).show();

                else if (userid.length() < 10)
                    Snackbar.make(rl_marqr, "Check Number ", Snackbar.LENGTH_SHORT).show();

                else
                */

                ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info = null;
                if (manager != null) {
                    info = manager.getActiveNetworkInfo();
                }
                if (info!=null)
                    checkqr();
                else
                    Toast.makeText(MarkQRActivity.this, "Check Internet. Failed to add.", Toast.LENGTH_SHORT).show();



            }
        });


    }


    private void ispresentalready(final String userid) {


        myRef.child("KR-" + userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Snackbar.make(rl_marqr, "Good to go.", Snackbar.LENGTH_SHORT).show();
                    btn_scan.setEnabled(true);
                } else

                {
                    img_lion.setImageResource(R.drawable.failed);
                    Snackbar.make(rl_marqr, "Please Register First", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Got it!", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    img_lion.setImageResource(R.drawable.roundlogo);
                                    clearall();
                                }
                            })
                            .show();
                   // Toast.makeText(MarkQRActivity.this, "Please Register First.", Toast.LENGTH_SHORT).show();

                }
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
            myRef.child("KR-" + userid).child("qrcode").setValue(QRCODEValue);

//            myRef.child("QRCODES").child(QRCODEValue).setValue("KR-"+userid);

            Snackbar.make(rl_marqr, "Awesome! Succesfully Set!", Snackbar.LENGTH_INDEFINITE).setAction("Okay!", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearall();
                }
            }).show();

        } else

        {
            Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();


        }
    }

    private void clearall() {
        et_uuid.setText("");
        btn_scan.setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.registermenu, menu);
        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.callarun) {
            new AlertDialog.Builder(MarkQRActivity.this)
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
            new AlertDialog.Builder(MarkQRActivity.this)
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

