package edu.calpoly.smuddulu.com.dogdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chitti on 5/22/2016.
 */
public class SignUp extends AppCompatActivity {
    protected EditText mFirstName;
    protected EditText mLastName;
    protected EditText mEmailText;
    protected Button mCreateButton;
    protected EditText mPassword;
    Firebase ref ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        Firebase.setAndroidContext(this);
       ref = new Firebase("https://woodapp.firebaseio.com");
      mFirstName=(EditText)findViewById(R.id.firstNameid);
        mLastName=(EditText)findViewById(R.id.LastNameid);
        mEmailText=(EditText)findViewById(R.id.editEmailAcc);
        mPassword=(EditText)findViewById(R.id.editPasswordAcc);
        mCreateButton = (Button) findViewById(R.id.buttonsignup);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailtext=mEmailText.getText().toString().trim();
                String passwordtext=mPassword.getText().toString();
                final String firstnametext=mFirstName.getText().toString();
                final String lastnametext=mLastName.getText().toString();

              ref.createUser(emailtext /*"sravya@firebaseio.com"*/, passwordtext /*"chity"*/, new Firebase.ValueResultHandler<Map<String, Object>>() {
                  @Override
                  public void onSuccess(Map<String, Object> result) {
                     /* Firebase firebaseRef = new Firebase(getResources().getString(R.string.firebase_url));*/
                      Firebase postRef = ref.child("users");
                      Firebase newPostRef = postRef.push();
                      Map<String, Object> post1 = new HashMap<>();
                      post1.put("firstName", firstnametext);
                      post1.put("initials", "" + firstnametext.toUpperCase().charAt(0)
                              + lastnametext.toUpperCase().charAt(0));
                      post1.put("lastName", lastnametext);
                      post1.put("username", emailtext);
                      post1.put("verified", false);
                      newPostRef.updateChildren(post1);
                      Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
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
