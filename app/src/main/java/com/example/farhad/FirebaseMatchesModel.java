package com.example.farhad;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FirebaseMatchesModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public FirebaseMatchesModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatch(Match match) {
        CollectionReference matchRef = db.collection("matches");
        matchRef.add(match);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchById(Match match) {
        DocumentReference matchRef = db.collection("matches").document(match.getUid());
        Map<String, Object> data = new HashMap<>();
        data.put("imageUrl", match.getImageUrl());
        data.put("liked", match.isLiked());
        data.put("name", match.getName());
        data.put("uid", match.getUid());
        data.put("lat", match.getLat());
        data.put("longitude", match.getLongitude());

        matchRef.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }
}