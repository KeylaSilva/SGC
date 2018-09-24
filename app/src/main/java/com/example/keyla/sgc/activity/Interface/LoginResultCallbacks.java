package com.example.keyla.sgc.activity.Interface;

import com.example.keyla.sgc.activity.Model.User;

//Interface com métodos de Callback do Login
public interface LoginResultCallbacks {
    void onSuccess(User user);
    void inProgress();
    void endProgress();
    void onError(int typeMessage);
}
