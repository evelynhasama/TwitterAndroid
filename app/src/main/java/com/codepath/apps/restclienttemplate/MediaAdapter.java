package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MediaAdapter extends  RecyclerView.Adapter<MediaAdapter.ViewHolder> {


    Context context;
    List<String> mediaUrls;

    // pass in context and list of tweets
    public MediaAdapter(Context context, List<String> mediaUrls){
        this.context = context;
        this.mediaUrls = mediaUrls;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
        return new MediaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        String media = mediaUrls.get(position);
        holder.bind(media);
    }

    @Override
    public int getItemCount() {
        if (mediaUrls.isEmpty()){
                return 0;
        }
        return mediaUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivMediaItem;

        public ViewHolder(View itemView) {

            super(itemView);
            ivMediaItem = itemView.findViewById(R.id.ivMediaItem);

        }

        public void bind(String media) {
            Glide.with(context).load(media).transform(new FitCenter(), new RoundedCorners(25)).into(ivMediaItem);
        }
    }
}