package com.kratos18.kratos2k18;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WinnersActivity extends AppCompatActivity {
    TextView tv_paperpresenation, tv_projectdisplay, tv_connections, tv_codewars, tv_trespassers, tv_googleit;
    TextView tv_promotions, tv_homicide, tv_prisonbreak, tv_midcitymadness, TV_mathiyosi;


    TextView tv_fp, tv_sp, tv_tp, tvevent;

    FirebaseDatabase database;
    DatabaseReference myRef, userref, proref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners);
        callviews();
        tvevent = findViewById(R.id.tvevent);
        tv_fp = findViewById(R.id.tvfp);
        tv_sp = findViewById(R.id.tvsp);
        tv_tp = findViewById(R.id.tvtp);


        tv_fp.setText("1");
        tv_sp.setText("2");
        tv_tp.setText("3");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Winners");
        userref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        proref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Referral");
        tv_fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = tv_fp.getText().toString();

                if (!user.equals("1"))
                    userref.child(user).addValueEventListener(new ValueEventListener() {
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

                            new MaterialDialog.Builder(WinnersActivity.this)
                                    .title("Details of " + user)
                                    .items(Details)
                                    .show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                else
                    Toast.makeText(WinnersActivity.this, "Please Choose One event.", Toast.LENGTH_SHORT).show();


            }
        });

        tv_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = tv_sp.getText().toString();
                if (!user.equals("2"))
                    userref.child(user).addValueEventListener(new ValueEventListener() {
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

                            new MaterialDialog.Builder(WinnersActivity.this)
                                    .title("Details of " + user)
                                    .items(Details)
                                    .show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                else
                    Toast.makeText(WinnersActivity.this, "Please Choose One event.", Toast.LENGTH_SHORT).show();


            }
        });

        tv_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = tv_tp.getText().toString();
//                Toast.makeText(WinnersActivity.this, ""+user, Toast.LENGTH_SHORT).show();
                if (!user.equals("3"))
                    userref.child(user).addValueEventListener(new ValueEventListener() {
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

                            new MaterialDialog.Builder(WinnersActivity.this)
                                    .title("Details of " + user)
                                    .items(Details)
                                    .show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                else
                    Toast.makeText(WinnersActivity.this, "Please Choose One event.", Toast.LENGTH_SHORT).show();

            }
        });

        tv_paperpresenation.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.paper_presentation));


                myRef.child(getString(R.string.paper_presentation)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                     /*   String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.paper_presentation))
                                .items(Details)
                                .show();*/


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


        tv_projectdisplay.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.project));

                myRef.child(getString(R.string.project)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                     /*   String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.project))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        tv_connections.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(R.string.connections);

                myRef.child(getString(R.string.connections)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                       /* String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.connections))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_codewars.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.codears));

                myRef.child(getString(R.string.codears)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);
/*
                        String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.codears))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_trespassers.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.trespassers));

                myRef.child(getString(R.string.trespassers)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                        /*String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.trespassers))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_googleit.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(R.string.Google_it);

                myRef.child(getString(R.string.Googleit)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                        /*String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.Google_it))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_homicide.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.homicie));

                myRef.child(getString(R.string.homicie)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                     /*   String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.Homicide))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_prisonbreak.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.prison));

                myRef.child(getString(R.string.prison)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                       /* String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.PrisonBreak))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        TV_mathiyosi.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.mathi));

                myRef.child(getString(R.string.mathi)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);

                       /* String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.Maathiyosi))
                                .items(Details)
                                .show();
*/
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_midcitymadness.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.midcity));

                myRef.child(getString(R.string.midcity)).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String winner1 = dataSnapshot.child("Winner1").getValue(String.class);
                        String winner2 = dataSnapshot.child("Winner2").getValue(String.class);
                        String winner3 = dataSnapshot.child("Winner3").getValue(String.class);

                        tv_fp.setText(winner1);
                        tv_sp.setText(winner2);
                        tv_tp.setText(winner3);
/*
                        String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.Midcity))
                                .items(Details)
                                .show();*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        tv_promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        //parent index

                        int max = -1;

                        DataSnapshot dswinner = null;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if ((int) ds.getChildrenCount() > max) {
                                max = (int) ds.getChildrenCount();
                                dswinner = ds;
                            }


                        }
                        assert dswinner != null;
                        String value = dswinner.getKey().trim();

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title(R.string.referral)
                                .content("Winner - " + value)
                                .positiveText("Okay")
                                .show();

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
        tv_promotions = findViewById(R.id.tvtvReferralWinner);

    }
}
