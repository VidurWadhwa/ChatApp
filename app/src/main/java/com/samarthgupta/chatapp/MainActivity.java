package com.samarthgupta.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText et1;
    Button bt1;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference();
    DatabaseReference ref1=firebaseDatabase.getReference("Message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.textView3);
        et1=(EditText)findViewById(R.id.editText);
        bt1=(Button)findViewById(R.id.button);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t= et1.getText().toString();
                FirebaseClass mes = new FirebaseClass();
                mes.setMessage(t);
                ref.child("Message").setValue(mes);
                Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();

            }
        });

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseClass firebaseClass;
                firebaseClass = dataSnapshot.getValue(FirebaseClass.class);
                String string = firebaseClass.getMessage();
                tv1.setText(string);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
