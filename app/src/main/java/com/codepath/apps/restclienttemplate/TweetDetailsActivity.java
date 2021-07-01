package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TweetDetailsActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvDisplayName;
    TextView tvTweetBody;
    TextView tvDateTime;
    ImageView ivProfilePic;
    Tweet tweet;
    RecyclerView rvMedia;
    MediaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher_twitter);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(" Tweet");

        tvTweetBody = findViewById(R.id.tvTweetBody);
        tvDisplayName = findViewById(R.id.tvDisplayName);
        tvDateTime = findViewById(R.id.tvDateTime);
        tvUsername = findViewById(R.id.tvUsername);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        rvMedia = findViewById(R.id.rvMedia);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        ArrayList<String> media = tweet.media;
        adapter = new MediaAdapter(this, media);
        // setup recycler view
        rvMedia.setLayoutManager(new LinearLayoutManager(this));
        rvMedia.setAdapter(adapter);

        tvUsername.setText("@" + tweet.user.screenName);
        tvDisplayName.setText(tweet.user.name);
        tvDateTime.setText(getRelativeTimeAgo(tweet.createdAt));
        tvTweetBody.setText(tweet.body);
        Glide.with(this).load(tweet.user.profileImageUrl).transform(new FitCenter(), new RoundedCorners(20)).into(ivProfilePic);

        ivProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TweetDetailsActivity.this, UserDetailsActivity.class);
                intent.putExtra(User.class.getSimpleName(), Parcels.wrap(tweet.user));
                startActivity(intent);
            }
        });
    }
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}