package com.example.keyla.sgc.activity.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.keyla.sgc.activity.Interface.LoginResultCallbacks;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModelFactory(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
    }

    //Cria e defini o databinding do ViewModel de Login
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LoginViewModel(loginResultCallbacks);
    }
}

