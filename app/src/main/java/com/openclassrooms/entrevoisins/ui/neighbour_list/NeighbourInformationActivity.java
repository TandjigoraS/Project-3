package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddNeighbourFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

public class NeighbourInformationActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_information);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapseLayout);
        ImageButton btnReturn = findViewById(R.id.back_button);


        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getSerializableExtra("EXTRA_NEIGHBOUR");

        collapsingToolbarLayout.setTitle(neighbour.getName());
        ImageView avatarImage = findViewById(R.id.neighbour_information_imageview_avatar_image);
        Glide.with(avatarImage.getContext())
                .load(neighbour.getAvatarUrl())
                .into(avatarImage);

        TextView nameNeighbourTitle = findViewById(R.id.neighbour_information_textview_avatar_name_title);
        nameNeighbourTitle.setText(neighbour.getName());
        nameNeighbourTitle.setTextSize(20);

        TextView address = findViewById(R.id.neighbour_information_textview_avatar_address);
        address.setText(neighbour.getAddress());
        address.setTextSize(13);

        TextView numberPhone = findViewById(R.id.neighbour_information_textview_avatar_phone_number);
        numberPhone.setText(neighbour.getPhoneNumber());
        numberPhone.setTextSize(13);

        TextView avatarUrl = findViewById(R.id.neighbour_information_textview_avatar_url);
        avatarUrl.setText(neighbour.getAvatarUrl());
        avatarUrl.setTextSize(13);


        TextView aboutMe = findViewById(R.id.neighbour_information_textview_avatar_about_me);
        aboutMe.setText(neighbour.getAboutMe());


        FloatingActionButton myFavourite = findViewById(R.id.neighbour_information_fab);


        myFavourite.setOnClickListener(view -> {

                    myFavourite.setImageResource(R.drawable.ic_star_yellow_24dp);
                    Snackbar.make(view, "This neighbour " + neighbour.getName()+" has been added to the favorites", 3000).show();
                    addFavoriteNeighbourList(neighbour);


            });

        btnReturn.setOnClickListener(v -> finish());


    }

    public void addFavoriteNeighbourList (Neighbour neighbour){
        Log.d("MY LOG", "SEND");
        EventBus.getDefault().post(new AddNeighbourFavEvent(neighbour));
}



}