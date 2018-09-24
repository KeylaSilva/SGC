package com.example.keyla.sgc.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keyla.sgc.R;
import com.example.keyla.sgc.activity.Interface.LoginResultCallbacks;
import com.example.keyla.sgc.activity.Model.User;
import com.example.keyla.sgc.activity.Model.ValidationEnum;
import com.example.keyla.sgc.activity.ViewModel.LoginViewModel;
import com.example.keyla.sgc.activity.ViewModel.LoginViewModelFactory;
import com.example.keyla.sgc.databinding.ActivityLoginBinding;

import es.dmoral.toasty.Toasty;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity implements LoginResultCallbacks{

    private ProgressDialog progressDialogLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Definindo o Binding da View de Login
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //Definindo o ViewModel de Login, utilizando os parametros esperados
        activityLoginBinding.setViewModel(ViewModelProviders.of(
                this,
                new LoginViewModelFactory(this))
                .get(LoginViewModel.class));
    }

    //Result Callback OnSucess do LoginResultsCallbacks
    @Override
    public void onSuccess(User userResponse) {
        //Após o sucesso do Login, realiza a chamada da activity Main
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Result Callback OnError do LoginResultsCallbacks
    @Override
    public void onError(int Message) {
        //Em caso de erro do Callback de login, verifica o tipo de mensagem de erro

        //Busca os labels a serem exibidos na página
        TextView txtViewEmailEmpty = (TextView)findViewById(R.id.textViewEmailInvalid);
        TextView txtViewPasswordEmpty = (TextView)findViewById(R.id.textViewPasswordInvalid);
        TextView txtLoginInvalid = (TextView)findViewById(R.id.textViewLoginInvalid);

        //Caso todos os campos estiverem vazios, exibe mensagem de preencher campos
        if (Message == ValidationEnum.FIELDS_EMPTY.valueValidation){
            txtViewEmailEmpty.setVisibility(VISIBLE);
            txtViewPasswordEmpty.setVisibility(VISIBLE);
            txtLoginInvalid.setVisibility(INVISIBLE);
        }
        //Caso o login não for preenchido, exibe mensagem de preencher Login
        else if(Message == ValidationEnum.LOGIN_EMPTY.valueValidation){
            txtViewEmailEmpty.setVisibility(VISIBLE);
            txtViewPasswordEmpty.setVisibility(INVISIBLE);
            txtLoginInvalid.setVisibility(INVISIBLE);
        }
        //Caso a senha não for preenchido, exibe mensagem de preencher Senha
        else if(Message == ValidationEnum.PASSWORD_EMPTY.valueValidation){
            txtViewEmailEmpty.setVisibility(INVISIBLE);
            txtViewPasswordEmpty.setVisibility(VISIBLE);
            txtLoginInvalid.setVisibility(INVISIBLE);
        }
        //Em caso de error 500, internal erro, exibir toast informando erro
        else if (Message == ValidationEnum.FAIL_REQUEST.valueValidation){
            txtViewEmailEmpty.setVisibility(INVISIBLE);
            txtViewPasswordEmpty.setVisibility(INVISIBLE);
            txtLoginInvalid.setVisibility(INVISIBLE);
            Toasty.error(this, "Erro ao realizar o login.", Toast.LENGTH_LONG).show();
        }
        //Caso usuário ou senha incorretos, exibir mensagem de usuário ou senha incorretos
        else if (Message == ValidationEnum.UNAUTHORIZED.valueValidation){
            txtViewEmailEmpty.setVisibility(INVISIBLE);
            txtViewPasswordEmpty.setVisibility(INVISIBLE);
            txtLoginInvalid.setVisibility(VISIBLE);
        }
    }

    //Result inProgress OnError do LoginResultsCallbacks
    @Override
    public void inProgress(){
        //Callback de progresso, exibindo o dialog executando
       progressDialogLogin = ProgressDialog.show(this, "", "Por favor, aguarde...");
    }

    //Result Callback endProgress do LoginResultsCallbacks
    @Override
    public void endProgress(){
        //após finalizar o progresso, esconder dialog
        progressDialogLogin.dismiss();
    }
}
