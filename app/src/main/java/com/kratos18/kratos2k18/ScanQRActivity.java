package com.kratos18.kratos2k18;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    Button btn_scanqr, btn_addtoevent;
    FirebaseDatabase database;
    DatabaseReference myRef, userref;
    TextView tv_eventname;
    TextView tv_memberscount;
    Button btn_viewparticipants;
    String eventname;
    ProgressDialog pd;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        btn_scanqr = findViewById(R.id.btn_scanqr);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        userref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Events");
        btn_addtoevent = findViewById(R.id.btn_addtoevent);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        eventname = pref.getString("selectedevent", null);
        tv_eventname = findViewById(R.id.tv_eventname);
        tv_eventname.setText(eventname); // getting Event name
        btn_viewparticipants = findViewById(R.id.btn_viewparticipants);
        tv_memberscount = findViewById(R.id.tvmemberscount);
        tv_memberscount.setText(R.string.Loading);

        pd = new ProgressDialog(ScanQRActivity.this);
        pd.setMessage("Loading Please Wait!");
        pd.show();

        isOnline();
        userref.child(eventname).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //  Toast.makeText(ScanQRActivity.this, eventname+" " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();

                // int count;
                if (dataSnapshot.getValue() == null) {
                    tv_memberscount.setText(getString(R.string.zerocountparticipants));
                    btn_viewparticipants.setEnabled(false);
                    pd.dismiss();


                } else {
                    int count = (int) dataSnapshot.getChildrenCount();

                    // Toast.makeText(ScanQRActivity.this, "" + dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                    if (count == 0) {
                        tv_memberscount.setText(getString(R.string.zerocountparticipants));
                        btn_viewparticipants.setEnabled(false);
                        pd.dismiss();
                    } else if (count > 0) {
                        btn_viewparticipants.setEnabled(true);
                        tv_memberscount.setText(getString(R.string.NoofParticipants) + count);
                        pd.dismiss();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        btn_viewparticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScanQRActivity.this, ViewParticipants.class));
            }
        });

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
        pd.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() != null) {

            //the value of qr code.

            Query query = myRef.orderByChild(getString(R.string.qrcode)).equalTo(result.getContents());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getChildrenCount()>0)
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        key = childSnapshot.getKey();
                        getthestudent(key);
                        // Log.i(TAG,key);
                    }
                    else
                    {
                        Toast.makeText(ScanQRActivity.this, "Student Not found , Contact Admin", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                    //    Toast.makeText(ScanQRActivity.this, key, Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else

        {
            Toast.makeText(this, "Scanning has been cancelled", Toast.LENGTH_SHORT).show();

            pd.dismiss();
        }
    }

    private void getthestudent(String key) {

        myRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                Toast.makeText(ScanQRActivity.this, "Pls wait.. " + student.getTextname(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScanQRActivity.this, ShowDetailsActivity.class);
                Gson gson = new Gson();
                String studentDataObjectAsAString = gson.toJson(student);
                pd.dismiss();
                intent.putExtra("MyStudentObjectAsString", studentDataObjectAsAString);

                startActivity(intent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.choosewinners:
                startActivity(new Intent(ScanQRActivity.this, ChooseWinners.class));
                return true;

            case R.id.callarun:
                new AlertDialog.Builder(ScanQRActivity.this)
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
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
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
            new AlertDialog.Builder(ScanQRActivity.this)
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



/*

            FirebaseDatabase.getInstance().getReference().child("Users")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Student student = snapshot.getValue(Student.class);


                                if (student != null && student.getQrcode().equals(result.getContents())) {
                                    Toast.makeText(ScanQRActivity.this, "Pls wait.. " + student.getTextname(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ScanQRActivity.this, ShowDetailsActivity.class);
                                    Gson gson = new Gson();
                                    String studentDataObjectAsAString = gson.toJson(student);
                                    intent.putExtra("MyStudentObjectAsString", studentDataObjectAsAString);

                                    startActivity(intent);
                                    break;
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
*/


