package com.example.keyla.sgc.activity.Model;

//Enum com os valores das validações do usuário
public enum ValidationEnum {
    FIELDS_EMPTY(1),
    LOGIN_EMPTY(2),
    PASSWORD_EMPTY(3)
    ,DATA_VALID(0),
    FAIL_REQUEST(500),
    UNAUTHORIZED(401);

    public int valueValidation;
    ValidationEnum(int value) {
        valueValidation = value;
    }
}
