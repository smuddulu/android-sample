package edu.calpoly.smuddulu.com.dogdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Chitti on 4/19/2016.
 */





    public class DogView extends LinearLayout{

/** Displays the DogName text. */
        private TextView m_DogNameText;
        private ImageView m_DogImage;
    private  Context m_context;

        /** The data version of this View, containing the joke's information. */
        private Dog m_Dog;

        /**
         * Basic Constructor that takes only an application Context.
         *
         * @param context
         *            The application Context in which this view is being added.
         *
         * @param dog
         * 			  The Joke this view is responsible for displaying.
         */
        public DogView(Context context, Dog dog) {
            super(context);
            m_context=context;
            // TODO
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.dog_view, this, true);

            m_DogNameText=(TextView)findViewById(R.id.DognameTextView);
            m_DogImage=(ImageView)findViewById(R.id.DogImage);
            setDog(dog);


        }

        /**
         * Mutator method for changing the Joke object this View displays. This View
         * will be updated to display the correct contents of the new Joke.
         *
         * @param dog
         *            The Joke object which this View will display.
         */
        public void setDog(Dog dog) {
            // TODO
            this.m_Dog = dog;
            m_DogNameText.setText(dog.name);
            Picasso.with(m_context)
                    .load(dog.imageURL)
                    .into(m_DogImage);
            this.requestLayout();
        }

    }


