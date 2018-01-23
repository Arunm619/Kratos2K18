package com.kratos18.kratos2k18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WinnersActivity extends AppCompatActivity {
    TextView tv_paperpresenation, tv_projectdisplay, tv_connections, tv_codewars, tv_trespassers, tv_googleit;
    TextView tv_homicide, tv_prisonbreak, tv_midcitymadness, TV_mathiyosi;


    TextView tv_fp, tv_sp, tv_tp, tvevent;

    FirebaseDatabase database;
    DatabaseReference myRef;


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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Winners");






        tv_paperpresenation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.paper_presentation));


                myRef.child(getString(R.string.paper_presentation)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


        tv_projectdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.project));

                myRef.child(getString(R.string.project)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        tv_connections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(R.string.connections);

                myRef.child(getString(R.string.connections)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_codewars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.codears));

                myRef.child(getString(R.string.codears)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_trespassers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.trespassers));

                myRef.child(getString(R.string.trespassers)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_googleit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(R.string.Google_it);

                myRef.child(getString(R.string.Google_it)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_homicide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.homicie));

                myRef.child(getString(R.string.homicie)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_prisonbreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.prison));

                myRef.child(getString(R.string.prison)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        TV_mathiyosi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.mathi));

                myRef.child(getString(R.string.mathi)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_midcitymadness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.midcity));

                myRef.child(getString(R.string.midcity)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        tv_fp.setText(dataSnapshot.child("Winner1").getValue(String.class));
                        tv_sp.setText(dataSnapshot.child("Winner2").getValue(String.class));
                        tv_tp.setText(dataSnapshot.child("Winner3").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
