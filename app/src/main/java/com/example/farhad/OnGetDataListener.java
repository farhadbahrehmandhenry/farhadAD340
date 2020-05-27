package com.example.farhad;

public interface OnGetDataListener<T> {
    void onSuccess(T dataResponse);
    void onFailure();
}

