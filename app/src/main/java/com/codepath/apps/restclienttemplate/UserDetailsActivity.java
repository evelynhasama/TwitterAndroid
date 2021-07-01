package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.User;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

public class UserDetailsActivity extends AppCompatActivity {

    TextView tvUserName;
    TextView tvNameName;
    TextView tvDescription;
    ImageView ivProfilePicture;
    ImageView ivProfileBanner;
    ImageView ivVerified;
    TextView tvFollowersCount;
    TextView tvFollowingCount;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher_twitter);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        tvDescription = findViewById(R.id.tvDescription);
        tvNameName = findViewById(R.id.tvNameName);
        tvUserName = findViewById(R.id.tvUserName);
        ivProfileBanner = findViewById(R.id.ivProfileBanner);
        ivVerified = findViewById(R.id.ivVerified);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        tvFollowersCount = findViewById(R.id.tvFollowersCount);
        tvFollowingCount = findViewById(R.id.tvFollowingCount);

        user = (User) Parcels.unwrap(getIntent().getParcelableExtra(User.class.getSimpleName()));

        tvDescription.setText(user.description);
        tvUserName.setText(user.screenName);
        tvNameName.setText(user.name);
        tvFollowersCount.setText(String.valueOf(user.followersCount));
        tvFollowingCount.setText(String.valueOf(user.friendsCount));

        Glide.with(this).load(user.profileImageUrl).circleCrop().into(ivProfilePicture);
        Glide.with(this).load(user.bannerImageUrl).placeholder(R.color.twitter_blue_30).centerCrop().into(ivProfileBanner);

        if (user.verified){
            ivVerified.setVisibility(View.VISIBLE);
        } else {
            ivVerified.setVisibility(View.INVISIBLE);
        }

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageFragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, user.userId, UserDetailsActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}