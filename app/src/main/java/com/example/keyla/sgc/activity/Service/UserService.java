package com.example.keyla.sgc.activity.Service;

import com.example.keyla.sgc.activity.Model.AutenticarEntrada;
import com.example.keyla.sgc.activity.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    //Defini a url relativa para o serviço de autenticação e defini o Método HTTP (POST)
    @POST("api/Alunos/AutenticarAluno")
    Call<User> autenticateUser(@Body AutenticarEntrada autenticarEntrada);
}
