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


        readdata();
        //send the array list to firebase...
//
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsdatafromcsv));


    }

    public void readdata() {

        InputStream is = getResources().openRawResource(R.raw.a);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String Line = null;
        try {
            reader.readLine();
            while ((Line = reader.readLine()) != null) {
                // Toast.makeText(this, "" + Line, Toast.LENGTH_SHORT).show();
                String[] tokens = Line.split(",");


                try {
                    Student student = new Student();
//[152, "2018-01-17 20:00:11", unread, Yamuna.S, "Velammal engineering college", yamunaumai3@gmail.com, CSE, 9790998105, No]

//                    Toast.makeText(this, "FORM ID = " + tokens[0] + " \n DATE =" + tokens[1] + "\nR/U =" + tokens[2] + "\n" +
//                            "Name =" + tokens[3] + "\nCLG =" + tokens[4] + "\nEMAIL =" + tokens[5] + "\n DEPT =" + tokens[6] + "\n" +
//                            " Phone =" + tokens[7] + "\n Y/N =" + tokens[8], Toast.LENGTH_SHORT).show();

                    student.setForm_id(Integer.parseInt(tokens[0]));
                    student.setForm_date(tokens[1]);
                    student.setStatus(tokens[2]);
                    student.setTextname(tokens[3]);
                    student.setCollegename(tokens[4]);
                    student.setEmail42(tokens[5]);
                    student.setDept(tokens[6]);
                    student.setTextphone(Long.parseLong(tokens[7]));
                    student.setMc4wp_checkbox(tokens[8]);
                    student.setUUID("KR-" + tokens[7]);
                    student.setQrcode("Nul");
                    student.setAte(false);
                    myRef.child(student.getUUID()).setValue(student);

                    studentsdatafromcsv.add(student);


                } catch (Exception e) {
                    Log.d(TAG, "readdata: " + Arrays.toString(tokens));
                    int wtf = Log.wtf(TAG, "readdata: " + e.getMessage());

                }


                //Form_id,Form_date,Status,Text-name,College-name,Email-42,Dept,Text-phone,Mc4wp_checkbox
//                14,"2018-01-13 15:32:23",unread,"syed jafer","st. joseph's institute of technology",syedjafer1997@gmail.com,IT,9176409201,No
                //
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
