package com.example.farhad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Matches> mData;

    public RecycleViewAdapter(Context mContext, List<Matches> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);

        view = mInflater.inflate(R.layout.matches, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final String title = mData.get(position).getTitle();

        holder.tv_match_title.setText(mData.get(position).getTitle());
        holder.img_match_thumbnail.setImageResource(mData.get(position).getThumbnail());

        holder.itemView.findViewById(R.id.likeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "you liked " + title, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_match_title;
        ImageView img_match_thumbnail;
        Matches currentItem;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_match_title = (TextView) itemView.findViewById(R.id.matchText);
            img_match_thumbnail = (ImageView) itemView.findViewById(R.id.matchImage);
        }
    }
}
