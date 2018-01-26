package com.example.khajan.expatassistance;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khajan.expatassistance.model.explore.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khajan on 11/17/17.
 */

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>{

    private Context mContext;
    private List<Event> mEventArrayList;
    private static final String TAG = "ExploreAdapter";

    public ExploreAdapter(Context context)
    {
        mContext=context;
    }

    public void setData(List<Event> eventArrayList)
    {
        mEventArrayList=eventArrayList;
    }

    public ExploreAdapter(Context context, ArrayList<Event> eventArrayList) {
        mContext=context;
        mEventArrayList=eventArrayList;
    }


    @Override
    public ExploreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.explore_row, parent, false);
        return new ExploreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ExploreViewHolder holder, int position) {

        final Event event=mEventArrayList.get(position);

        if(event!=null)
        {
            holder.textView1.setText(event.getTitle());
            holder.textView2.setText(event.getDescription());
            Log.d(TAG, "onBindViewHolder: "+event.getImageUrl());
            Picasso.with(mContext).load(event.getImageUrl())
                    .error(R.drawable.default_banner)
                    .into(holder.banner_image);
            if(event.isFavorite())
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.liked,
                            mContext.getApplicationContext().getTheme()));
                } else {
                    holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.liked));
                }
            }

            holder.banner_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext,ExploreDetailedActivity.class);
                    mContext.startActivity(intent);
                }
            });

            holder.save_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!event.isFavorite())
                    {
                        //make fav
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.liked,
                                    mContext.getApplicationContext().getTheme()));
                        } else {
                            holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.liked));
                        }
                        event.setFavorite(true);
                    }
                    else{
                        //put default
                        //make fav
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_like,
                                    mContext.getApplicationContext().getTheme()));
                        } else {
                            holder.save_image.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_like));
                        }
                        event.setFavorite(false);
                    }

                }
            });

}
        }

    @Override
    public int getItemCount() {
        return mEventArrayList.size();
    }

    public class ExploreViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView profile_image,banner_image,save_image;
        public TextView textView1,textView2;

        public ExploreViewHolder(View itemView) {
            super(itemView);
            profile_image=itemView.findViewById(R.id.profile_image);
            banner_image=itemView.findViewById(R.id.banner_image);
            save_image=itemView.findViewById(R.id.save_icon);
            textView1=itemView.findViewById(R.id.user_name);
            textView2=itemView.findViewById(R.id.description_text);
        }
    }
}
