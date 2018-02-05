package com.example.lenovo.bussinessportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailID;
    private EditText _password;
    private TextView login;
    private TextView createnew;
    private FirebaseAuth firebaseauth;
    private ProgressDialog progessdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailID=(EditText)findViewById(R.id.Login_email);
        _password=(EditText)findViewById(R.id.Login_password);
        login=(TextView) findViewById(R.id.login);
        createnew=(TextView)findViewById(R.id.Back_to_register);
        createnew.setOnClickListener(this);
        login.setOnClickListener(this);
        progessdialog=new ProgressDialog(this);
        firebaseauth=FirebaseAuth.getInstance();

    }
    private void LoginUser(){
        String email=emailID.getText().toString().trim();
        String password=_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        //login function will happen
        progessdialog.setMessage("Login Please Wait...");
        progessdialog.show();
        firebaseauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new
                OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "LoginSuccessful!", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(loginActivity.this,profile.class);
                            startActivity(i);
                            progessdialog.dismiss();
                        } else {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast.makeText(loginActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_LONG).show();
                          // Toast.makeText(loginActivity.this, "Could not login please try again ..!", Toast.LENGTH_SHORT).show();
                            progessdialog.dismiss();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view){
        if(view == login){
            //login here
            LoginUser();
        }
        if(view == createnew){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);

        }

    }
}
