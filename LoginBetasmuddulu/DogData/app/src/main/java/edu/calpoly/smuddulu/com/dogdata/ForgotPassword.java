package edu.calpoly.smuddulu.com.dogdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by Chitti on 5/23/2016.
 */
public class ForgotPassword extends AppCompatActivity {

    protected Button mResetButton;
    protected EditText mNewPassword;
    protected EditText mTempPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        Firebase.setAndroidContext(this);
        mResetButton = (Button) findViewById(R.id.resetbutton);
        mNewPassword = (EditText) findViewById(R.id.newPwd);
        mTempPassword = (EditText) findViewById(R.id.tmpPwd);


        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase ref = new Firebase("https://woodapp.firebaseio.com");
                String newPasswordText=mNewPassword.getText().toString();
                ref.changePassword("sravani.mudduluru@gmail.com", mTempPassword.getText().toString(), newPasswordText, new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Password Reset", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(myIntent);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}