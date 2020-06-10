package com.example.farhad;

import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchHolder> {
    private List<MatchItem> matches;
    private OnListFragmentInteractionListener listener;

    private static final String TAG = MatchAdapter.class.getName();


    public MatchAdapter(List<MatchItem> mMatches, OnListFragmentInteractionListener mListener) {
        matches = mMatches;
        listener = mListener;
    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches, parent, false);
        return new MatchHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchHolder holder, int position) {
        if (matches != null && position < matches.size()) {
            Resources res = holder.itemView.getContext().getResources();
            MatchItem match = matches.get(position);

            holder.matchName.setText(match.name);
            Picasso.get().load(match.imageUrl).into(holder.matchImage);

            if (match.liked) {
                holder.matchFavBtn.setImageResource(R.drawable.loke);
            } else {
                holder.matchFavBtn.setImageResource(R.drawable.ic_like_blue_1);
            }

            holder.matchFavBtn.setOnClickListener(view -> {
                if (listener != null) {
                    if (match.liked) {
                        holder.matchFavBtn.setImageResource(R.drawable.loke);
                        StringBuilder likeMsg = new StringBuilder(res.getString(R.string.LikeMsg))
                                .append(holder.matchName.getText());

                        Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                        likeMsg.setLength(0);
                    } else {
                        holder.matchFavBtn.setImageResource(R.drawable.ic_like_blue_1);
                        StringBuilder likeMsg = new StringBuilder(res.getString(R.string.LikeMsg))
                                .append(holder.matchName.getText());

                        Toast.makeText(holder.itemView.getContext(), likeMsg, Toast.LENGTH_SHORT).show();
                        likeMsg.setLength(0);
                    }
                    listener.onListFragmentInteraction(match);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (matches != null) {
            return matches.size();
        }

        return 0;
    }
}