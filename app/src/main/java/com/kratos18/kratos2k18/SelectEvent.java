package com.kratos18.kratos2k18;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;

public class SelectEvent extends AppCompatActivity {
    RadioGroup rg_events;
    RadioButton rb_connections, rb_codewars, rb_trespassers,
            rb_googleit,
            rb_futsal,
            rb_midcitymadness,
            rb_prisonbreak,
            rb_maathiyosi,
            rb_homicide,
            rb_gaming;

    Button btn_selectevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_event);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();
        String a = pref.getString("selectedevent",null);
        if (a != null) {
            startActivity(new Intent(SelectEvent.this, ScanQRActivity.class));
            finish();
        }

        btn_selectevent = findViewById(R.id.btn_selecteventbtn);
        btn_selectevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg_events.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select an event", Toast.LENGTH_SHORT).show();
                } else {
                    // get selected radio button from radioGroup
                    int selectedId = rg_events.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedId);
                    Toast.makeText(getApplicationContext(), selectedRadioButton.getText().toString() + " is selected", Toast.LENGTH_SHORT).show();

                    editor.putString("selectedevent", selectedRadioButton.getText().toString().trim()); // Storing string
                    editor.apply();
                    editor.commit();


                    startActivity(new Intent(SelectEvent.this, ScanQRActivity.class));
                    finish();
                }
            }
        });
        setuprb();


    }

    private void setuprb() {
        rg_events = findViewById(R.id.rg_events);
        rb_connections = findViewById(R.id.rb_connections);
        rb_codewars = findViewById(R.id.rb_codewars);
        rb_trespassers = findViewById(R.id.rb_trespassers);
        rb_googleit = findViewById(R.id.rb_googleit);
        rb_futsal = findViewById(R.id.rb_futsal);
        rb_midcitymadness = findViewById(R.id.rb_midcitymadness);
        rb_prisonbreak = findViewById(R.id.rb_prisonbreak);
        rb_maathiyosi = findViewById(R.id.rb_maathiyosi);
        rb_homicide = findViewById(R.id.rb_homicide);
        rb_gaming = findViewById(R.id.rb_gaming);

    }
}
