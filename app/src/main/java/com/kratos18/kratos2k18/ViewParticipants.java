package com.kratos18.kratos2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.MessageFormat;
import java.util.ArrayList;

public class ViewParticipants extends AppCompatActivity {
    TextView tvparticipants;
    String eventname;
    FirebaseDatabase database;
    DatabaseReference myRef, userref;
    ListView lv_participants;
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participants);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        database = FirebaseDatabase.getInstance();

        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Events");
        userref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        tvparticipants = findViewById(R.id.tvparticipants);

        eventname = pref.getString("selectedevent", null);
        tvparticipants.setText(eventname + " " + getString(R.string.p));


        lv_participants = findViewById(R.id.lv_participants);


        final ArrayList<String> pList = new ArrayList<>();

        listAdapter = new ArrayAdapter<String>(this, R.layout.listitem, pList);
        lv_participants.setAdapter(listAdapter);

//        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pList);
//        lv_participants.setAdapter(listAdapter);

        myRef.child(eventname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Student student = ds.getValue(Student.class);
                    String nameanduuid = MessageFormat.format("{0} <-> {1}", student.getUUID(), student.getTextname());
                    pList.add(nameanduuid);
                    listAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        lv_participants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Object o = lv_participants.getItemAtPosition(i);
                String str = (String) o;
                String[] splits = str.split("<->");
                String UUID = splits[0].trim();

                userref.child(UUID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot ds) {
                        Student student = ds.getValue(Student.class);

                        String name = student.getTextname();
                        String clg = student.getCollegename();
                        String Phone = String.valueOf(student.getTextphone());
                        String Dept = student.getDept();


                        String[] Title = {"Name", "Phone", "College", "Dept"};
                        String[] Details = {"Name : " + name
                                , "Phone : " + Phone,
                                "Clg   : " + clg,
                                "Dept  : " + Dept};


                        new MaterialDialog.Builder(ViewParticipants.this)
                                .title("Details of " + name)
                                .items(Details)
                                .itemsCallback(new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                        switch (which) {
                                            case 1:
                                                Toast.makeText(ViewParticipants.this, "" + dialog.getItems().get(which).toString(), Toast.LENGTH_SHORT).show();

                                                String textPhone = dialog.getItems().get(which).toString();
                                                String splits[] = textPhone.split(":");
                                                String phoneNumber = splits[1].trim();

                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                                                startActivity(intent);
                                                break;

                                           default:
                                                Toast.makeText(ViewParticipants.this, dialog.getItems().get(which).toString(), Toast.LENGTH_SHORT).show();
                                                

                                        }

                                    }
                                })
                                .show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                // Toast.makeText(ViewParticipants.this, UUID, Toast.LENGTH_SHORT).show();


            }
        });


    }
}
