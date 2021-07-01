package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.UsersAdapter;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_ID = "ARG_ID";
    public static final String TAG = "PageFragment";

    private int mPage;
    private List<User> followers;
    private List<User> following;
    Context context;
    View view;
    int mUserId;
    TwitterClient client;
    UsersAdapter followersAdapter;
    UsersAdapter followingAdapter;


    public static PageFragment newInstance(int page, int userId) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_ID, userId);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        mUserId = getArguments().getInt(ARG_ID);
        client = TwitterApplication.getRestClient(context);

        if (mPage==1) {
            followers = new ArrayList<>();
            getFollowers(mUserId);
        } else {
            following = new ArrayList<>();
            getFollowing(mUserId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment, container, false);

        RecyclerView rvUsers = view.findViewById(R.id.rvUsers);

        if (mPage==1) {
            followersAdapter = new UsersAdapter(getContext(), followers);
            rvUsers.setAdapter(followersAdapter);
            rvUsers.setLayoutManager(new LinearLayoutManager(context));

        } else {
            followingAdapter = new UsersAdapter(getContext(), following);
            rvUsers.setAdapter(followingAdapter);
            rvUsers.setLayoutManager(new LinearLayoutManager(context));
        }
        return view;
    }

    // gets the users who is following the user
    private void getFollowers(int userId) {

        client.getFollowers(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "getFollowers onSuccess!" + json.toString());
                JSONObject jsonObject = json.jsonObject;
                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            followers.add(User.fromJson(jsonArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, "followers size " + followers.size());
                    followersAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "getFollowers onFailure!", throwable);
            }
        }, userId);

    }

    // gets the users who the user is following
    private void getFollowing(int userId) {

        client.getFriends(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "getFriends onSuccess!" + json.toString());
                JSONObject jsonObject = json.jsonObject;
                JSONArray jsonArray = null;
                try {
                    jsonArray = jsonObject.getJSONArray("users");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            following.add(User.fromJson(jsonArray.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, "following size " + following.size());
                    followingAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "getFriends onFailure!", throwable);
            }
        }, userId);

    }


}
