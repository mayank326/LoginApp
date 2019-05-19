package com.farmerskorner.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name,age,gender,country,password;
    Button save;
    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        country=findViewById(R.id.country);

        save=findViewById(R.id.save);

        databaseUser = FirebaseDatabase.getInstance().getReference("Users");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username=name.getText().toString().trim();
                String Userage=age.getText().toString().trim();
                String Usergender=gender.getText().toString().trim();
                String Usercountry=country.getText().toString().trim();
                String userPassword=password.getText().toString().trim();

                String id = databaseUser.push().getKey();
                Users user=new Users(id,Username,Userage,Usergender,Usercountry,userPassword);

                databaseUser.child(id).setValue(user);
            }
        });



    }
}
