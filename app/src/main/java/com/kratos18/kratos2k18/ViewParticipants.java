package com.kratos18.kratos2k18;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewParticipants extends AppCompatActivity {
    TextView tvparticipants;
    String eventname;
    FirebaseDatabase database;
    DatabaseReference myRef;
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

        tvparticipants = findViewById(R.id.tvparticipants);

        eventname = pref.getString("selectedevent", null);
        tvparticipants.setText(eventname + " " + getString(R.string.p));


        lv_participants = findViewById(R.id.lv_participants);


        final ArrayList<String> pList = new ArrayList<>();

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pList);
        lv_participants.setAdapter(listAdapter);


        myRef.child(eventname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Student student = ds.getValue(Student.class);
                    String nameanduuid = student.getTextname() + " <-> " + student.getUUID();
                    pList.add(nameanduuid);
                    listAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pList);
        lv_participants.setAdapter(listAdapter);


    }
}
