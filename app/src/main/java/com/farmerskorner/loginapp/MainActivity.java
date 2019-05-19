package com.farmerskorner.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
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

    EditText name,password;
    TextView signUp;
    Button submit;
    DatabaseReference databaseUser;

    public static final String PREFS_NAME = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
        }

        name=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signUp=findViewById(R.id.signup);
        signUp=findViewById(R.id.signup);
        submit=findViewById(R.id.login);

        databaseUser = FirebaseDatabase.getInstance().getReference("Users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
*/
                final String user = name.getText().toString().trim();
                final String pass =password.getText().toString().trim();

                final String[] id = new String[1];
                final String[] name = new String[1];
                final String[] gender = new String[1];
                final String[] country = new String[1];
                final String[] age = new String[1];
                final String[] password1 = new String[1];
                final int[] count = {0};
                databaseUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        count[0] =0;
                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                if(dataSnapshot1.child("userName").getValue().equals(user) && dataSnapshot1.child("userPassword").getValue().equals(pass)){

                                   id[0] = (String) dataSnapshot1.child("id").getValue();
                                    name[0] = (String) dataSnapshot1.child("userName").getValue();
                                    name[0] = (String) dataSnapshot1.child("userName").getValue();
                                   gender[0] = (String) dataSnapshot1.child("userGender").getValue();
                                   country[0] = (String) dataSnapshot1.child("userCountry").getValue();
                                   age[0] = (String) dataSnapshot1.child("userAge").getValue();
                                   password1[0] = (String) dataSnapshot1.child("userPassword").getValue();
                                    count[0]=1;


                                }
                        }

                        if(count[0]==1) {
                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("logged", "logged");
                            editor.putString("key1",id[0]);
                            editor.putString("key2",name[0]);
                            editor.putString("key3",gender[0]);
                            editor.putString("key4",country[0]);
                            editor.putString("key5",age[0]);
                            editor.putString("key6",password1[0]);
                            editor.commit();
                            Toast.makeText(MainActivity.this, "Successfull Login", Toast.LENGTH_SHORT).show();

                            Intent i1 = new Intent(MainActivity.this, Profile.class);
                            startActivity(i1);
                            finish();
                        }

                        else{
                            Toast.makeText(MainActivity.this,"User not Exist",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value

                    }
                });
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,SignUp.class);
                startActivity(i1);
            }
        });
    }
}
