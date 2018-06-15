package com.example.group3.csci3130_group3_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textView;
    private Button logout_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();
        textView=(TextView) findViewById(R.id.textView_userN);
        textView.setText(user.getEmail()+" login");
        logout_bt=(Button) findViewById(R.id.button_logout);
        logout_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v== logout_bt)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
