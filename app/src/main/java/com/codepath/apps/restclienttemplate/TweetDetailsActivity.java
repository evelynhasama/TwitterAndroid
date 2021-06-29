package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    TextView tvUsername;
    TextView tvDisplayName;
    TextView tvTweetBody;
    TextView tvDateTime;
    ImageView ivProfilePic;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tvTweetBody = findViewById(R.id.tvTweetBody);
        tvDisplayName = findViewById(R.id.tvDisplayName);
        tvDateTime = findViewById(R.id.tvDateTime);
        tvUsername = findViewById(R.id.tvUsername);
        ivProfilePic = findViewById(R.id.ivProfilePic);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        tvUsername.setText("@" + tweet.user.screenName);
        tvDisplayName.setText(tweet.user.name);
        tvDateTime.setText(tweet.createdAt);
        tvTweetBody.setText(tweet.body);
        Glide.with(this).load(tweet.user.profileImageUrl).transform(new FitCenter(), new RoundedCorners(20)).into(ivProfilePic);
    }
}