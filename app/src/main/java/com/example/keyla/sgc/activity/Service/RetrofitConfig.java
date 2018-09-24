package com.example.keyla.sgc.activity.Service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
public class RetrofitConfig {
    private final Retrofit retrofit;

    //Defini as configurações do Retrofit, onde serão realizadas as chamadas do serviço
    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://wsgestaocursos.azurewebsites.net/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    //Cria a rota de busca do Retrofit para a classe UserService
    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }
}
