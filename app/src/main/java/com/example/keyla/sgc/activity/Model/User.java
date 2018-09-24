package com.example.keyla.sgc.activity.Model;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Sucesso",
        "Erro",
        "Cpf",
        "Nome",
        "Endereco",
        "Estado",
        "Municipio",
        "Telefone",
        "Email"
})
public class User extends BaseObservable {

    @JsonProperty("Sucesso")
    private Boolean sucesso;
    @JsonProperty("Erro")
    private String erro;
    @JsonProperty("Cpf")
    private String cpf;
    @JsonProperty("Nome")
    private String nome;
    @JsonProperty("Endereco")
    private String endereco;
    @JsonProperty("Estado")
    private String estado;
    @JsonProperty("Municipio")
    private String municipio;
    @JsonProperty("Telefone")
    private String telefone;
    @JsonProperty("Email")
    private String email;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private  String login;
    private  String password;

    @JsonProperty("Sucesso")
    public Boolean getSucesso() {
        return sucesso;
    }

    @JsonProperty("Sucesso")
    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    @JsonProperty("Erro")
    public String getErro() {
        return erro;
    }

    @JsonProperty("Erro")
    public void setErro(String erro) {
        this.erro = erro;
    }

    @JsonProperty("Cpf")
    public String getCpf() {
        return cpf;
    }

    @JsonProperty("Cpf")
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonProperty("Nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("Nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("Endereco")
    public String getEndereco() {
        return endereco;
    }

    @JsonProperty("Endereco")
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @JsonProperty("Estado")
    public String getEstado() {
        return estado;
    }

    @JsonProperty("Estado")
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonProperty("Municipio")
    public String getMunicipio() {
        return municipio;
    }

    @JsonProperty("Municipio")
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @JsonProperty("Telefone")
    public String getTelefone() {
        return telefone;
    }

    @JsonProperty("Telefone")
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    public User() {
    }

    public User(@NonNull String login, @NonNull String password) {
        this.login = login;
        this.password = password;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    //Método de validação dos dados do usuário
    public int isValidData(){
        //Caso usuário e senha em branco
        if (TextUtils.isEmpty(getLogin()) && TextUtils.isEmpty(getPassword())){
            return ValidationEnum.FIELDS_EMPTY.valueValidation;
        }
        //Caso o login em branco
        else if (TextUtils.isEmpty(getLogin())){
            return ValidationEnum.LOGIN_EMPTY.valueValidation;
        }
        //Caso a senha em branco
        else if (TextUtils.isEmpty(getPassword())){
            return ValidationEnum.PASSWORD_EMPTY.valueValidation;
        }

        //Senão, validação correta dos dados
        return ValidationEnum.DATA_VALID.valueValidation;
    }
}
