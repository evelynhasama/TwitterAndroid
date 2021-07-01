package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {

    public String name;
    public String screenName;
    public String profileImageUrl;
    public String description;
    public int followersCount;
    public int friendsCount;
    public boolean verified;
    public String bannerImageUrl;
    public int userId;


    // empty constructer for parcel
    public User(){}

    public static User fromJson(JSONObject jsonObject) throws JSONException {
        User user = new User();
        user.name = jsonObject.getString("name");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url_https");
        user.description = jsonObject.getString("description");
        user.followersCount = jsonObject.getInt("followers_count");
        user.friendsCount = jsonObject.getInt("friends_count");
        user.verified = jsonObject.getBoolean("verified");
        if (jsonObject.has("profile_banner_url")){
            user.bannerImageUrl = jsonObject.getString("profile_banner_url");
        }
        user.userId = jsonObject.getInt("id");

        return user;
    }
}
