package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class PageFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Followers", "Following" };
    public static final String TAG = "PageFragmentAdapter";
    int userId;
    Context context;

    public PageFragmentAdapter(@NonNull @NotNull FragmentManager fm, int behavior, int userId, Context context) {
        super(fm, behavior);
        this.userId = userId;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1, userId);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}

