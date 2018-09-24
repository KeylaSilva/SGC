package com.example.keyla.sgc.activity.Model;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "Login",
        "Senha",
})
//Model utilizado para enviar os dados através do RequestBody
public class AutenticarEntrada {
    @JsonProperty("Login")
    private  String Login;
    @JsonProperty("Senha")
    private  String Senha;

    public AutenticarEntrada(@NonNull String login, @NonNull String password) {
        this.Login = login;
        this.Senha = password;
    }
}
