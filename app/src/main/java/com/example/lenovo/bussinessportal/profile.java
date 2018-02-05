package com.example.lenovo.bussinessportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity implements View.OnClickListener{
    private Button add;
    private Button logout;
    private EditText name;
    private EditText contactnumber;
    private EditText additionaldetail;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseUser;
    private profile_database data=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //button declaration

        logout = (Button) findViewById(R.id.nav_logout);
        add=(Button)findViewById(R.id.profile_insert);

        //editText declaration
        name=(EditText)findViewById(R.id.profile_name);
        contactnumber=(EditText)findViewById(R.id.profile_ContactNo);
        additionaldetail=(EditText)findViewById(R.id.profile_detail);

        //firebase authentication declaration

        firebaseAuth = FirebaseAuth.getInstance();

        //declaring database

        databaseUser= FirebaseDatabase.getInstance().getReference("User");
        //progress dialog declaration

        progressDialog = new ProgressDialog(this);

        //adding listener on logout and add buttons

        logout.setOnClickListener(this);
        add.setOnClickListener(this);
    }
    private void Logout_User(){
        firebaseAuth.signOut();
        progressDialog.dismiss();
        Intent i=new Intent(this,loginActivity.class);
        startActivity(i);
    }
    private void insert_in_firebase_database(){
        String contactno=contactnumber.getText().toString().trim();
        String pro_name=name.getText().toString().trim();
        String detail=additionaldetail.getText().toString().trim();

        if(TextUtils.isEmpty(contactno)){
            //if text field of name will be empty
            Toast.makeText(this,"Please fill the form!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(pro_name)){
            //if text field of name will be empty
            Toast.makeText(this,"Please fill the form!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(detail)){
            //if text field of name will be empty
            Toast.makeText(this,"Please fill the form!",Toast.LENGTH_SHORT).show();
            return;
        }

        //now data will insert in the database
        String id=databaseUser.push().getKey();//it will generate a primary key and store its value in id;
        data=new profile_database(pro_name,contactno,detail,id);
        databaseUser.child(id).setValue(data);
        Toast.makeText(this,"successful!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view){
        if(view ==logout){
            progressDialog.setMessage("Logging out......");
            progressDialog.show();
            Logout_User();
        }
        if(view == add){
            insert_in_firebase_database();
        }
    }
}
