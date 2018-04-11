package edu.calpoly.smuddulu.com.dogdata;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by Chitti on 4/19/2016.
 */
public class LoginActivity extends AppCompatActivity {
    protected EditText mLogintext;
    protected EditText mPasswordtext;
    protected Button mloginbutton;
    protected Button mCreateButton;
    protected Button mForgotPwdButton;
    Firebase ref;
    DataSnapshot snapShot;
    boolean mverified = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://woodapp.firebaseio.com");
        mCreateButton = (Button) findViewById(R.id.createButton);
        mLogintext = (EditText) findViewById(R.id.editEmail);
        mPasswordtext = (EditText) findViewById(R.id.editPassword);
        mloginbutton = (Button) findViewById(R.id.buttonLogin);
        mForgotPwdButton = (Button) findViewById(R.id.forgotPwdId);

        Log.v("email_entered", mPasswordtext.toString());

        mloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailtext = mLogintext.getText().toString().trim();
                final String passwordtext = mPasswordtext.getText().toString();
                ref.child("users").addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot usersSnapShot : dataSnapshot.getChildren()) {

                            UserData user = usersSnapShot.getValue(UserData.class);
                            if (user.getUsername().equals(emailtext) && user.isVerified() == true) {
                                //mverified = true;
                                Toast.makeText(getApplicationContext(), "Account Verified", Toast.LENGTH_SHORT).show();
                                ref.authWithPassword(emailtext /*"sravanim@firebaseio.com"*/, passwordtext /*"chitti"*/, new Firebase.AuthResultHandler() {
                                    @Override

                                    public void onAuthenticated(AuthData authData) {
                                        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(myIntent);

                      /*  Log.v("E_UID", authData.getUid());
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Toast.makeText(getApplicationContext(), "User Logged In", Toast.LENGTH_SHORT).show();*/

                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError firebaseError) {
                                        Log.v("E_Error:", firebaseError.getMessage());
                                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();

                                    }

                                });

                            }

                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

                Log.v("Login Successful", mLogintext.toString());
            }
        });
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(myIntent);
            }
        });
        mForgotPwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase ref = new Firebase("https://woodapp.firebaseio.com");
                ref.resetPassword("sravani.mudduluru@gmail.com", new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Email Confirmation sent", Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(getApplicationContext(), ForgotPassword.class);
                        startActivity(myIntent);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

}
