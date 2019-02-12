package com.kratos18.kratos2k18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

        InputStream is = getResources().openRawResource(R.raw.newa);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String Line;
        try {
            int count = 0;
            reader.readLine();
            while ((Line = reader.readLine()) != null) {
                // Toast.makeText(this, "" + Line, Toast.LENGTH_SHORT).show();
                String[] tokens = Line.split(",");

                   // count ++;



                // Name , College , Email , Dept, Phone
//"|","|__college","|__degree","|__department","|__email","|__phone","|__username","|__year"

                try {
                    Student student = new Student();


                    if (tokens[5].length() != 12)
                        continue;


                    String name = tokens[6].substring(1,tokens[6].length()-1);
                    String College = tokens[1].substring(1,tokens[1].length()-1);
                    String email = tokens[4].substring(1,tokens[4].length()-1);
                    Long phone = Long.parseLong(tokens[5].substring(1,tokens[5].length()-1));
                    String phoneInString = phone.toString();
                    String degree = tokens[2].substring(1,tokens[2].length()-1);
                    String dept = tokens[3].substring(1,tokens[3].length()-1);

                    student.setTextname(name);
                    student.setCollegename(College);
                    student.setEmail(email);
                    student.setDept(degree+ " " + dept);
                    student.setTextphone(phone);
                    student.setParticipatedevents("null");
                    student.setUUID("KR-" + phoneInString);
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
