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
    RadioButton rb_paperpresentation,
                rb_clashofcodes,
        rb_techtreasurehunt,rb_deadlockeddb, rb_googleit, rb_gadgetandgizmos,
    rb_murderinmultiplayer, rb_prisonbreak, rb_pitchimpossible,rb_comicquiz,
    rb_boxcricket,rb_gaming;

    Button btn_selectevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_event);
        setuprb();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();
        String a = pref.getString("selectedevent", null);
      /*  if (a != null) {
            startActivity(new Intent(SelectEvent.this, ScanQRActivity.class));
            finish();
        }*/

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
                    String nameoftheevent = selectedRadioButton.getText().toString().trim();
                    //  Toast.makeText(SelectEvent.this, ""+nameoftheevent, Toast.LENGTH_SHORT).show();
                    editor.putString("selectedevent", nameoftheevent); // Storing string
                    editor.apply();
                    editor.commit();


                    startActivity(new Intent(SelectEvent.this, ScanQRActivity.class));
                    finish();
                }
            }
        });



    }

    private void setuprb() {
       //tech
        rb_paperpresentation = findViewById(R.id.rb_paperpresentation);
        rb_clashofcodes = findViewById(R.id.rb_clash_of_codes);

        rb_techtreasurehunt = findViewById(R.id.rb_tech_treasure_hunt);
        rb_deadlockeddb = findViewById(R.id.rb_dead_locked_db);

        rb_googleit = findViewById(R.id.rb_googleit);
        rb_gadgetandgizmos = findViewById(R.id.rb_gadgets_gizmos);


        //non tech

        rb_murderinmultiplayer = findViewById(R.id.rb_murder_in_multiplayer);
        rb_prisonbreak = findViewById(R.id.rb_prisonbreak);

        rb_pitchimpossible = findViewById(R.id.rb_pitch_impossible);
        rb_comicquiz = findViewById(R.id.rb_comic_quiz);

        rb_boxcricket = findViewById(R.id.rb_box_cricket);
        rb_gaming = findViewById(R.id.rb_gaming);


        rg_events = findViewById(R.id.rg_events);
    }
}
