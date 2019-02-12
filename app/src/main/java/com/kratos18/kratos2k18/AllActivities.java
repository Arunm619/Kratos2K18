package com.kratos18.kratos2k18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllActivities extends AppCompatActivity {
    Button btn_referral, btn_admin, btn_choosewinner, btn_winners, btn_register, btn_selectevent, btn_scanqr, btn_markqr, btn_csv, btn_lunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_activities);
        btn_referral = findViewById(R.id.btn_referral);

        btn_winners = findViewById(R.id.btn_winners);
        btn_csv = findViewById(R.id.btn_csv);
        btn_register = findViewById(R.id.btn_register);
        btn_markqr = findViewById(R.id.btn_markqr);
        btn_scanqr = findViewById(R.id.btn_scanqr);
        btn_selectevent = findViewById(R.id.btn_selectevent);
        btn_lunch = findViewById(R.id.btn_lunch);
        btn_choosewinner = findViewById(R.id.btn_choosewinner);
        btn_referral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(AllActivities.this, Referral.class));

            }
        });
        btn_admin = findViewById(R.id.btn_admin);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, Admin.class));
            }
        });
        btn_choosewinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, ChooseWinners.class));

            }
        });
        btn_winners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, WinnersActivity.class));

            }
        });

        btn_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, LunchActivity.class));

            }
        });
        btn_scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, ScanQRActivity.class));
            }
        });
        btn_selectevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, SelectEvent.class));
            }
        });


        btn_markqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, MarkQRActivity.class));

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllActivities.this, RegisterNew.class));

            }
        });

        btn_csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllActivities.this, "Fuck off bitch", Toast.LENGTH_SHORT).show();
            }
        });

        btn_csv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(AllActivities.this, CollectCSV.class));
                return true;
            }
        });
        //    btn_csv.setEnabled(false);
    }
}
