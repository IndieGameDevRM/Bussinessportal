package com.example.lenovo.bussinessportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends AppCompatActivity implements View.OnClickListener{
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    //private DrawerLayout mDrawlayout;
    //private ActionBarDrawerToggle mtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=(Button)findViewById(R.id.nav_logout);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        logout.setOnClickListener(this);
        //mDrawlayout=(DrawerLayout)findViewById(R.id.drawer);
        //mtoggle=new ActionBarDrawerToggle(this,mDrawlayout,R.string.Open,R.string.Close);
       // mtoggle.syncState();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*@Override
     public boolean onOptionsItemSelected(MenuItem item){
        if(mtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
    private void Logout_User(){
        firebaseAuth.signOut();
        progressDialog.dismiss();
        Intent i=new Intent(this,loginActivity.class);
        startActivity(i);
    }
    @Override
    public void onClick(View view){
        if(view ==logout){
            progressDialog.setMessage("Logging out......");
            progressDialog.show();
            Logout_User();
        }
    }
}
