package com.codepath.apps.restclienttemplate.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.MediaAdapter;
import com.codepath.apps.restclienttemplate.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public static final String TAG = "UsersAdapter";

    Context context;
    List<User> users;

    // pass in context and list of users
    public UsersAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        if (users.isEmpty()){
            return 0;
        }
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivUserProfileImage;
        TextView tvUserUsername;
        TextView tvUserNameName;
        TextView tvUserDescription;

        public ViewHolder(View itemView) {

            super(itemView);
            ivUserProfileImage = itemView.findViewById(R.id.ivUserProfileImage);
            tvUserDescription = itemView.findViewById(R.id.tvUserDescription);
            tvUserNameName = itemView.findViewById(R.id.tvUserNameName);
            tvUserUsername = itemView.findViewById(R.id.tvUserUsername);

        }

        public void bind(User user) {
            Glide.with(context).load(user.profileImageUrl).circleCrop().into(ivUserProfileImage);
            tvUserUsername.setText("@"+user.screenName);
            tvUserNameName.setText(user.name);
            tvUserDescription.setText(user.description);
        }
    }
}
