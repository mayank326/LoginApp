package com.farmerskorner.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    EditText name,age,gender,country;
    Button edit,save,logout;
    DatabaseReference databaseUser;
    public static final String PREFS_NAME = "LoginPrefs";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

          name=findViewById(R.id.name);
          age=findViewById(R.id.age);
          gender=findViewById(R.id.gender);
          country=findViewById(R.id.country);

          edit=findViewById(R.id.edit);
          save=findViewById(R.id.save);
          logout=findViewById(R.id.logout);

          name.setFocusable(false);
          age.setFocusable(false);
        gender.setFocusable(false);
        country.setFocusable(false);

        name.setBackground(Drawable.createFromPath("@android:color/transparent"));
        age.setBackground(Drawable.createFromPath("@android:color/transparent"));
        gender.setBackground(Drawable.createFromPath("@android:color/transparent"));
        country.setBackground(Drawable.createFromPath("@android:color/transparent"));

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final SharedPreferences.Editor editor = settings.edit();



        final String valueId = settings.getString("key1", "");
        databaseUser = FirebaseDatabase.getInstance().getReference("Users").child(valueId);


        name.setText( settings.getString("key2", ""));
          age.setText( settings.getString("key5", ""));
          gender.setText( settings.getString("key3", ""));
          country.setText( settings.getString("key4", ""));
          final String newPassword= settings.getString("key6", "");

          edit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  name.setFocusableInTouchMode(true);
                  age.setFocusableInTouchMode(true);
                  gender.setFocusableInTouchMode(true);
                  country.setFocusableInTouchMode(true);

                  save.setVisibility(view.VISIBLE);
              }
          });
          save.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  String newName=name.getText().toString().trim();
                  String newAge=age.getText().toString().trim();
                  String newGender=gender.getText().toString().trim();
                  String newCountry=country.getText().toString().trim();

                  Users user=new Users(valueId, newName,newAge, newGender,newCountry,newPassword);
                    databaseUser.setValue(user);

                  save.setVisibility(view.INVISIBLE);
                  name.setFocusable(false);
                  age.setFocusable(false);
                  gender.setFocusable(false);
                  country.setFocusable(false);

              }
          });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("logged");
                editor.commit();
                Intent i1=new Intent(Profile.this,MainActivity.class);
                startActivity(i1);
                finish();
            }
        });

    }
}
