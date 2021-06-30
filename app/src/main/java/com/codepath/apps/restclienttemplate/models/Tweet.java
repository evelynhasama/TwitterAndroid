package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Tweet {

    public String body;
    public String createdAt;
    public User user;
    public String imageUrl;
    public ArrayList<String> media;

    // empty constructer for parcel
    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        JSONObject entity = jsonObject.getJSONObject("entities");

        if (entity.has("media")) {
            JSONArray tempmedia = entity.
                    getJSONArray("media");
            int i = 0;
            ArrayList<String> tempMediaArray = new ArrayList<>();
            while (i < tempmedia.length()){
                if (i == 0){
                    tweet.imageUrl = tempmedia.getJSONObject(0).getString("media_url_https");
                }

                tempMediaArray.add(tempmedia.getJSONObject(i).getString("media_url_https"));
                i++;
            }
            tweet.media = tempMediaArray;
        } else {
            tweet.imageUrl = "";
            tweet.media = new ArrayList<>();
        }
        Log.d("TweetClass", tweet.imageUrl);
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
