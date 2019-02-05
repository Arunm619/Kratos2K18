package com.kratos18.kratos2k18;

import android.annotation.SuppressLint;
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
    TextView tv_paperpresenation, tv_clashofcodes, tv_tech_treasurehunt, tv_deadlockdb, tv_googleit, tv_gadgets;

    TextView tv_murderinmulti, tv_prisonbreak, tv_pitchimpossible, tv_comicquiz, tv_boxcricket, tv_gaming;

    TextView tv_fp, tv_sp, tv_tp, tvevent, tv_totalcount;

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

        tv_totalcount = findViewById(R.id.totalcount);


        tv_fp.setText(R.string.select_event);
        tv_sp.setText(R.string.select_event);
        tv_tp.setText(R.string.select_event);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Winners");
        userref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Users");
        proref = database.getReferenceFromUrl("https://kratos2k18-896f6.firebaseio.com/Referral");

        userref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long a = dataSnapshot.getChildrenCount();
                //Toast.makeText(WinnersActivity.this, "Size count:"+dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                tv_totalcount.setText(getString(R.string.total) + a);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tv_fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = tv_fp.getText().toString();

                if (!user.equals(R.string.select_event))
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
                if (!user.equals(R.string.select_event))
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
                if (!user.equals(R.string.select_event))
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


        //technical

       /* tv_paperpresenation.setOnClickListener(new View.OnClickListener() {
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

                     *//*   String[] Details = {"Winner 1: " + winner1, "Winner 2: " + winner2, "Winner 3: " + winner3};

                        new MaterialDialog.Builder(WinnersActivity.this)
                                .title("Details of " + getString(R.string.paper_presentation))
                                .items(Details)
                                .show();*//*


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });*/

//tech

        tv_paperpresenation.setOnClickListener(new View.OnClickListener() {
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


        tv_clashofcodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(R.string.clash_of_codes);

                myRef.child(getString(R.string.clash_of_codes)).addValueEventListener(new ValueEventListener() {
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

        tv_tech_treasurehunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.tech_treasure_hunt));

                myRef.child(getString(R.string.tech_treasure_hunt)).addValueEventListener(new ValueEventListener() {
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

        tv_deadlockdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.dead_locked_db));

                myRef.child(getString(R.string.dead_locked_db)).addValueEventListener(new ValueEventListener() {
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

        tv_googleit.setOnClickListener(new View.OnClickListener() {
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

        tv_gadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.gadgets_and_gizmos));

                myRef.child(getString(R.string.gadgets_and_gizmos)).addValueEventListener(new ValueEventListener() {
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


        //non tech

        tv_murderinmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.murder_in_multiplayer));

                myRef.child(getString(R.string.murder_in_multiplayer)).addValueEventListener(new ValueEventListener() {
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


        tv_prisonbreak.setOnClickListener(new View.OnClickListener() {
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

        tv_pitchimpossible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.pitch_impossible));

                myRef.child(getString(R.string.pitch_impossible)).addValueEventListener(new ValueEventListener() {
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


        tv_comicquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.comic_quiz));

                myRef.child(getString(R.string.comic_quiz)).addValueEventListener(new ValueEventListener() {
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


        tv_boxcricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.box_cricket_futsal));

                myRef.child(getString(R.string.box_cricket_futsal)).addValueEventListener(new ValueEventListener() {
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


        tv_gaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvevent.setText(getString(R.string.gaming));

                myRef.child(getString(R.string.gaming)).addValueEventListener(new ValueEventListener() {
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


    }


    private void callviews() {
        //tech
        tv_paperpresenation = findViewById(R.id.tvtvpaperpresentation);
        tv_clashofcodes = findViewById(R.id.tvtvclashofcodes);
        tv_tech_treasurehunt = findViewById(R.id.tvtvtechtreasurehunt);
        tv_deadlockdb = findViewById(R.id.tvtvdeadlockeddb);
        tv_googleit = findViewById(R.id.tvtvGoogleit);
        tv_gadgets = findViewById(R.id.tvtvGadgetsandgizmo);


        //non tech
        tv_murderinmulti = findViewById(R.id.tvtvmurderinmultiplayer);
        tv_prisonbreak = findViewById(R.id.tvtvprisonbreak);
        tv_pitchimpossible = findViewById(R.id.tvtvpitchimpossible);
        tv_comicquiz = findViewById(R.id.tvtvcomicquiz);
        tv_boxcricket = findViewById(R.id.tvtvboxcricket);
        tv_gaming = findViewById(R.id.tvtvgaming);


    }
}
