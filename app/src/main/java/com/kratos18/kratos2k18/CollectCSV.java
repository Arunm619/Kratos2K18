package com.kratos18.kratos2k18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectCSV extends AppCompatActivity {
    private static final String TAG = "Arun checks...";
    List<Student> studentsdatafromcsv = new ArrayList<>();


    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectcsv);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        // ListRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/AddedNumbers");

        readdata();
        //send the array list to firebase...
//
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsdatafromcsv));


    }

    public void readdata() {

        InputStream is = getResources().openRawResource(R.raw.a);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String Line;
        try {
            reader.readLine();
            while ((Line = reader.readLine()) != null) {
                // Toast.makeText(this, "" + Line, Toast.LENGTH_SHORT).show();
                String[] tokens = Line.split(",");
                // Name , College , Email , Dept, Phone

                try {
                    Student student = new Student();

                    if (tokens[6].length() != 10)
                        continue;
                    student.setTextname(tokens[3]);
                    student.setCollegename(tokens[4]);
                    student.setEmail(tokens[5]);
                    student.setDept(tokens[7]);
                    student.setTextphone(Long.parseLong(tokens[6]));
                    student.setParticipatedevents("null");
                    student.setUUID("KR-" + tokens[6]);
                    student.setQrcode("Null");
                    myRef.child(student.getUUID()).setValue(student);

                    //  String rkey = ListRef.push().getKey();
                    //   ListRef.child(rkey).setValue(Long.parseLong(tokens[7]));
                    studentsdatafromcsv.add(student);


                } catch (Exception e) {
                    Log.d(TAG, "readdata: " + Arrays.toString(tokens));
                    int wtf = Log.wtf(TAG, "readdata: " + e.getMessage());

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
