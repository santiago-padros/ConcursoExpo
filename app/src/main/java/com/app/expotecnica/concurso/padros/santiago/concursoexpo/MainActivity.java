package com.app.expotecnica.concurso.padros.santiago.concursoexpo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    VideoView videoViewLandscape;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();
        /*String str = "https://firebasestorage.googleapis.com/v0/b/concursoexpotecnica.appspot.com/o/20160331_211535.mp4?alt=media&token=1ed48fe8-a639-4786-831c-ba49cf98793c";
        Uri uri = Uri.parse(str);
        videoViewLandscape.setVideoURI(uri);
        //progressBarLandScape.setVisibility(View.VISIBLE);
        videoViewLandscape.requestFocus();
        videoViewLandscape.start();*/
    }
    /* TextView Numero1;
    TextView Numero2;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button Sumar1;
    Button Sumar2;
    private TextView btnComentario;
     private TextView btnLogOut;
    DatabaseReference RutaRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference NumeroRef1 = RutaRef.child("Numero1");
    DatabaseReference NumeroRef3 = RutaRef.child("Numero2");
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

    DatabaseReference currentUserDB3;



    String buttonValue;
    String buttonValue1;
    String Voto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Numero1 = (TextView) findViewById(R.id.textView1);
        Numero2 = (TextView) findViewById(R.id.textView2);
        Sumar1 = (Button) findViewById(R.id.button1);
        Sumar2 = (Button) findViewById(R.id.button2);
        btnComentario = (TextView)  findViewById(R.id.button4);


        btnLogOut = (TextView) findViewById(R.id.button3);







        btnComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();


            }
        });
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                    mAuth.signOut();


            }
        });
        mAuth = FirebaseAuth.getInstance();
        //mAuth.signOut();

    }



    protected void sendEmail() {
        String[] TO = {"santiago47s47s@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        currentUserDB3 = mDatabase.child(mAuth.getCurrentUser().getUid()).child("voto");
        btnLogOut.setVisibility(View.INVISIBLE);
        btnComentario.setVisibility(View.INVISIBLE);
        if(mAuth.getCurrentUser().getUid().equals("L9iS29Qyuxe8Q3zcqTCjCZzXrCf2")){
            btnLogOut.setVisibility(View.VISIBLE);
            btnComentario.setVisibility(View.VISIBLE);
        }
        NumeroRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 buttonValue = dataSnapshot.getValue(String.class);



                Numero1.setText(buttonValue);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Sumar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //NumeroRef2.setValue("1");
                String resu=String.valueOf(Long.parseLong(buttonValue)+1);
                NumeroRef1.setValue(resu);



                Toast.makeText(getApplicationContext(), "Thanks for voting!", Toast.LENGTH_SHORT).show();



                currentUserDB3.setValue("Si");








            }
        });
        currentUserDB3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Voto = dataSnapshot.getValue(String.class);
                if (Voto.equals("Si")) {
                    Sumar1.setVisibility(View.INVISIBLE);
                    Sumar2.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







        NumeroRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                buttonValue1 = dataSnapshot.getValue(String.class);
                Numero2.setText(buttonValue1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Sumar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resu=String.valueOf(Long.parseLong(buttonValue1)+1);
                NumeroRef3.setValue(resu);
                currentUserDB3.setValue("Si");

                Toast.makeText(getApplicationContext(), "Thanks for voting!", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
