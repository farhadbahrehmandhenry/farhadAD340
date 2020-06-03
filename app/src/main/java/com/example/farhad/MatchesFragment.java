package com.example.farhad;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
    FirebaseMatchesViewModel viewModel;

    public static MatchesFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        MatchesFragment fragment = new MatchesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new FirebaseMatchesViewModel();
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.matches_fragment, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public Button like_btn;
        public Context context;

        @SuppressLint("SetTextI18n")
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, FirebaseMatchesViewModel viewModel) {
            super(inflater.inflate(R.layout.matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.matchImage);
            name = (TextView) itemView.findViewById(R.id.matchText);
            like_btn = itemView.findViewById(R.id.likeButton);
            context = itemView.getContext();
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private int length;
        private ArrayList<Match> matches;
        private FirebaseMatchesViewModel viewModel;

        ContentAdapter(Context context, FirebaseMatchesViewModel viewModel) {
            this.viewModel = viewModel;
            context.getResources();
            viewModel = new FirebaseMatchesViewModel();
            viewModel.getMatches((fb_matches -> {
                this.matches = fb_matches;
                length = matches.size();
                notifyDataSetChanged();
            }));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, viewModel);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (this.matches != null) {
                Match match = this.matches.get(position % this.matches.size());
                Picasso.get().load(match.getImageUrl()).into(holder.picture);
                holder.name.setText(match.getName());

                holder.like_btn.setText(match.isLiked() ? R.string.UNLIKE : R.string.LIKE);
                holder.like_btn.setOnClickListener(view -> {
                    if (!match.isLiked()) {
                        //Liked someone
                        match.setLiked(true);
                        holder.like_btn.setText(R.string.UNLIKE);
                        Toast.makeText(holder.itemView.getContext(),holder.context.getString(R.string.LikeMsg) + holder.name.getText() , Toast.LENGTH_SHORT).show();
                    } else {
                        match.setLiked(false);
                        holder.like_btn.setText(holder.context.getString(R.string.LIKE));
                        Toast.makeText(holder.itemView.getContext(), holder.context.getString(R.string.UnlikedMsg) + holder.name.getText(), Toast.LENGTH_SHORT).show();
                    }

                    viewModel.updateMatchItem(match);
                });
            }
        }

        @Override
        public int getItemCount() {
            return length;
        }
    }
}