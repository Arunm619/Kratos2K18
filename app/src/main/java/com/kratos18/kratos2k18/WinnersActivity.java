package com.kratos18.kratos2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WinnersActivity extends AppCompatActivity {
    TextView tv_paperpresenation, tv_projectdisplay, tv_connections, tv_codewars, tv_trespassers, tv_googleit;
    TextView tv_homicide, tv_prisonbreak, tv_midcitymadness, TV_mathiyosi;


    TextView tv_fp, tv_sp, tv_tp, tvevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);
        callviews();
        tvevent = findViewById(R.id.tvevent);
        tv_fp = findViewById(R.id.tvfp);
        tv_sp = findViewById(R.id.tvsp);
        tv_tp = findViewById(R.id.tvtp);


        tv_fp.setText("Manoj");
        tv_sp.setText("Madhu");
        tv_tp.setText("Lenin");


        tv_paperpresenation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.paper_presentation));

                tv_fp.setText("Manoj");
                tv_sp.setText("Madhu");
                tv_tp.setText("Lenin");

            }
        });


        tv_projectdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.project));

            }
        });


        tv_connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText("Connections ");

            }
        });

        tv_codewars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.codears));

            }
        });

        tv_trespassers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.trespassers));

            }
        });

        tv_googleit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText("Google It");

            }
        });

        tv_homicide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.homicie));

            }
        });

        tv_prisonbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.prison));

            }
        });


        TV_mathiyosi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.mathi));

            }
        });

        tv_midcitymadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.midcity));

            }
        });
    }


    private void callviews() {

        tv_codewars = findViewById(R.id.tvtvcodewars);
        tv_connections = findViewById(R.id.tvtvconnections);
        tv_googleit = findViewById(R.id.tvtvGoogleit);
        tv_homicide = findViewById(R.id.tvtvhomicide);
        tv_midcitymadness = findViewById(R.id.tvtvMidcity);
        tv_prisonbreak = findViewById(R.id.tvtvPrisonBreak);
        TV_mathiyosi = findViewById(R.id.tvtvMaathiyosi);
        tv_paperpresenation = findViewById(R.id.tvtvpaperpresentation);
        tv_projectdisplay = findViewById(R.id.tvtvprojectdisplay);
        tv_trespassers = findViewById(R.id.tvtvtrespassers);

    }
}
