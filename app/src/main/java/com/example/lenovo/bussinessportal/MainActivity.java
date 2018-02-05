package com.example.lenovo.bussinessportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText emailID;
    private EditText _password;
    private TextView _Register;
    private TextView alreadylogin;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseauth=FirebaseAuth.getInstance();
        progressdialog=new ProgressDialog(this);
        emailID= (EditText) findViewById(R.id.email);
        _password= (EditText) findViewById(R.id.password);
        _Register=(TextView)findViewById(R.id.login);
        alreadylogin=(TextView)findViewById(R.id.Create_new);
        _Register.setOnClickListener(this);
        alreadylogin.setOnClickListener(this);


    }
    private void registering_user()
    {   String email=emailID.getText().toString().trim();
        String password=_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please password",Toast.LENGTH_SHORT).show();
        }
    //user will register here
        progressdialog.setMessage("Registering.......");
        progressdialog.show();
        firebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if (task.isSuccessful()){
                            //user is successfully registered & login
                            //we will start the new activity here
                            Toast.makeText(MainActivity.this, "Register successful......!", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,loginActivity.class);
                            startActivity(i);
                        }else {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast.makeText(MainActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(MainActivity.this, "Could not register.Please try again......!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
        );
    }

    @Override
    public void onClick(View view) {
        if(view==_Register){
            registering_user();

        }else if(view==alreadylogin){
            Intent i=new Intent(this,loginActivity.class);
            startActivity(i);
            //click another activity
        }
    }
}
