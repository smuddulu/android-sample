package edu.calpoly.smuddulu.com.dogdata;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.FileReader;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
    class Dog {
        public String name;
        public String imageURL;
        public Dog() {
            // empty default constructor, necessary for Firebase to be able to deserialize blog posts
        }
        public String getName() {
            return name;
        }
        public String getImage() {
            return imageURL;
        }
    }


public class MainActivity extends AppCompatActivity {

    protected ArrayList<Dog> m_dogsarray;
    protected ListView m_dogslayout;
    protected  DogListAdapter m_dogslistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       // getSupportActionBar().setIcon(R.drawable.paw);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
       // Firebase.getDefaultConfig().setPersistenceEnabled(true);
       // ActionBar actionBar = getActionBar();
        //actionBar.setIcon(R.drawable.paw);
        m_dogsarray=new ArrayList<Dog>();
        m_dogslayout=(ListView)findViewById(R.id.listView);
        m_dogslistadapter=new DogListAdapter(this,m_dogsarray);
        m_dogslayout.setAdapter(m_dogslistadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  Firebase.getDefaultConfig().setPersistenceEnabled(true);

        // Attach an listener to read the data at our posts reference
        Firebase ref = new Firebase("https://woodapp.firebaseio.com/dogs");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " dogs");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Dog dog = postSnapshot.getValue(Dog.class);
                   // System.out.println(dog.getName());
                    m_dogsarray.add(dog);
                    m_dogslistadapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }
}
