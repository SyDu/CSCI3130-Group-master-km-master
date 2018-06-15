package com.example.group3.csci3130_group3_project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login_bt;
    private Button regs_bt;
    private Button fid_bt;
    private EditText psw_editText;
    private  EditText userN_editText;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            //login state
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        psw_editText=(EditText) findViewById(R.id.editText_psw);
        userN_editText=(EditText) findViewById(R.id.editText_name);
        fid_bt=(Button) findViewById(R.id.button_fid);
        regs_bt=(Button) findViewById(R.id.button_regs);
        login_bt=(Button)findViewById(R.id.button_login);
        progressDialog=new ProgressDialog(this);
        login_bt.setOnClickListener(this);
        regs_bt.setOnClickListener(this);


    }

    private  void user_login()
    {
        String userN=userN_editText.getText().toString().trim();
        String psw=psw_editText.getText().toString().trim();

        //check input states
        if(TextUtils.isEmpty(userN))
        {
            Toast.makeText(this,"Enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(psw))
        {
            Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Login .....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userN,psw).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                }
            }
        });


    }
    @Override
    public void onClick(View v) {
        if (v==login_bt)
        {
            user_login();
        }

        if (v==regs_bt)
        {
            finish();
            //go to the register page
        }
    }
}
