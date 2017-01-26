package com.samarthgupta.chatapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    EditText et1,et2,et3;
    Button bt1;
    String sender,receiver,message;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference();
    DatabaseReference ref1 = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.textView3);
        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        bt1=(Button)findViewById(R.id.button);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message= et1.getText().toString();
                sender = et2.getText().toString();
                receiver = et3.getText().toString();
                FirebaseClass obj1 = new FirebaseClass(sender,receiver,message);
                ref.child("A").setValue(obj1);
                Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
            }
        });


        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                message= et1.getText().toString();
                sender = et2.getText().toString();
                receiver = et3.getText().toString();

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                FirebaseClass obj2 = snap.getValue(FirebaseClass.class);
                if(obj2.getSender()==receiver){
                    Log.i("HEY", obj2.getSender());
                }

                 }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });




    }
}
