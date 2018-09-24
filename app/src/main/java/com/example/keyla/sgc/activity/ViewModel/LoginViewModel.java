package com.example.keyla.sgc.activity.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;

import com.example.keyla.sgc.R;
import com.example.keyla.sgc.activity.Interface.LoginResultCallbacks;
import com.example.keyla.sgc.activity.LoginActivity;
import com.example.keyla.sgc.activity.Model.AutenticarEntrada;
import com.example.keyla.sgc.activity.Model.User;
import com.example.keyla.sgc.activity.Model.ValidationEnum;
import com.example.keyla.sgc.activity.Service.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel{
    private User user;
    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;

        this.user = new User();
    }

    //Método observador, definido no campo login na view activity_login
    //com ele, é possível captar e transportar dados através do método afterTextChanged
    public TextWatcher getLoginTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String login = editable.toString();
                user.setLogin(login);
            }
        };
    }

    //Método observador, definido no campo password na view activity_login
    //com ele, é possível captar e transportar dados através do método afterTextChanged
    public TextWatcher getPasswordTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                user.setPassword(password);
            }
        };
    }

    //Método definido na view activity_login, no botão Login, para realizar a
    //lógica ao clicar em 'Login'
    public void onLoginClicked(View view) {

        //Realiza a validacao de dados (preenchimento de Login e Senha)
        int errorCode = user.isValidData();

        //Caso esteja correto, realiza a tentativa de login
        if (errorCode == 0){

            autenticateUser(user.getLogin(), user.getPassword());

        }
        //Senão, informa o código do erro através do Callback
        else {
            loginResultCallbacks.onError(errorCode);
        }
    }

    //Método para realizar a tentativa de Login
    public void autenticateUser(String login, String password) {
        loginResultCallbacks.inProgress();

        //Instancia objeto a ser enviado no request body da requisicao
        AutenticarEntrada requestBody = new AutenticarEntrada(login,password);

        //Instancia a chamada realizada através do Retrofit e chama o método buscarAluno
        Call<User> call = new RetrofitConfig().getUserService().autenticateUser(requestBody);

        //Defini a chamada na fila
        call.enqueue(new Callback<User>() {

            //Caso a resposta aconteça, realiza a lógica abaixo
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();

                //autenticacao com sucesso
                if (userResponse !=  null && userResponse.getSucesso() && user.getLogin() != null){
                    loginResultCallbacks.onSuccess(userResponse);
                }
                //Usuario ou senha não encontrados
                else  if(userResponse !=  null && !userResponse.getSucesso() && userResponse.getErro() == null){
                    loginResultCallbacks.onError(ValidationEnum.UNAUTHORIZED.valueValidation);
                }
                loginResultCallbacks.endProgress();
            }
            //Em caso de erro, realiza a lógica abaixo
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loginResultCallbacks.onError(ValidationEnum.FAIL_REQUEST.valueValidation);
            }
        });
    }
}
