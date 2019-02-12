package com.kratos18.kratos2k18;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class Admin extends AppCompatActivity {


    private static final String TAG ="ADMIN ACTIVITY " ;
    Button btn_scanqrfullinfo, btn_submit;
    EditText Et_input_uuid;
    FirebaseDatabase database;
    DatabaseReference myRef, ListRef;
    String person;
    TextView tv_UUID;
    String key;
    ProgressDialog pd;


    TextView tv_name, tv_clgname, tv_phone, tv_deptname, tv_participatedevents;
    LinearLayout ll;
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
        tv_participatedevents.setText(R.string.loading);
        tv_phone = findViewById(R.id.tv_phonenumber);
        tv_name = findViewById(R.id.tv_name);

        btn_submit = findViewById(R.id.btn_submit);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");

        pd = new ProgressDialog(Admin.this);
        pd.setMessage("Loading.");

         ll = findViewById(R.id.ll_card);

        if(tv_name.getText().equals(getString(R.string.Loading)))
            ll.setVisibility(View.GONE);

        //Log.wtf("Cookie","Casper");

        btn_scanqrfullinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkqr();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(Et_input_uuid.getWindowToken(), 0);

                person = "KR-" + Et_input_uuid.getText().toString();
                if (person.length() == 13) {

                    findtheuser();
                } else {
                    Toast.makeText(Admin.this, "Check Phone Number", Toast.LENGTH_SHORT).show();
                    ll.setVisibility(View.GONE);
                }
            }
        });

    }

    void findevents(final Student student) {
        //final StringBuffer events = new StringBuffer();
     //   DatabaseReference eref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Events");


      //  tv_participatedevents.setText(new String(events));

    }

    private void findtheuser() {
        myRef.child(person).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Student student = dataSnapshot.getValue(Student.class);

                        if (student != null) {
                            setviews(student);
                            ll.setVisibility(View.VISIBLE);

                        } else {

                            Toast.makeText(Admin.this, "Student does not exist!", Toast.LENGTH_SHORT).show();
                            ll.setVisibility(View.GONE);

                        }////findevents(student);

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

                    Log.i(TAG,dataSnapshot.getChildrenCount()+"");
                    if(dataSnapshot.getChildrenCount()>0)


                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        key = childSnapshot.getKey();
                        getthestudent(key);
                         Log.i(TAG,key);
                    }

                    else
                    {
                        pd.dismiss();

                        ll.setVisibility(View.GONE);
                        Toast.makeText(Admin.this, "QR CODE IS NULL , Please MARK QR", Toast.LENGTH_LONG).show();
                        Log.i(TAG,"NOT FOUND SUDENT");
                       // Toast.makeText(this, "Student Not Found", Toast.LENGTH_SHORT).show();
                    }                    //



                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else
        {
            Toast.makeText(this, "Cancelled Scanning!", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }

    private void getthestudent(String key) {


        myRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
               // Toast.makeText(Admin.this, "Pls wait.. " + student.getTextname(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin.this, ShowDetailsActivity.class);
                Gson gson = new Gson();
                String studentDataObjectAsAString = gson.toJson(student);

                setviews(student);

                ll.setVisibility(View.VISIBLE);
               // findevents(student);
                pd.dismiss();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}