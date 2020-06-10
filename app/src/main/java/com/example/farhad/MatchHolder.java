package com.example.farhad;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MatchHolder extends RecyclerView.ViewHolder {
    ImageView matchImage;
    TextView matchName;
    ImageView matchFavBtn;

    MatchHolder(@NonNull View itemView) {
        super(itemView);
        matchImage = itemView.findViewById(R.id.matchImage);
        matchName = itemView.findViewById(R.id.matchText);
        matchFavBtn = itemView.findViewById(R.id.likeButton);
    }
}
