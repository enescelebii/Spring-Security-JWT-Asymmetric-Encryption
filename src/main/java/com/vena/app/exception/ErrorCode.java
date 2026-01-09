package com.vena.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Old password does not match" , BAD_REQUEST ),
    INVALID_CURRENT_PASSWORD( "INVALID_CURRENT_PASSWORD" , "Invalid current password" , BAD_REQUEST ),
    ACCOUNT_ALREADY_DEACTIVATED( "ACCOUNT_ALREADY_DEACTIVATED" , "Account is already deactivated" , BAD_REQUEST ),
    ACCOUNT_ALREADY_ACTIVATED( "ACCOUNT_ALREADY_ACTIVATED" , "Account is already activated" , BAD_REQUEST ),
    EMAIL_ALREADY_EXISTS( "EMAIL_ALREADY_EXISTS" , "Email already exists" , BAD_REQUEST ),
    PHONE_NUMBER_ALREADY_EXISTS( "PHONE_NUMBER_ALREADY_EXISTS" , "Phone number already exists" , BAD_REQUEST ),
    PASSWORD_MISMATCH( "PASSWORD_MISMATCH" , "Passwords do not match" , BAD_REQUEST ),
    ERR_USER_DISABLED("ERR_USER_DISABLED","User is disabled", UNAUTHORIZED),
    BAD_CREDENTIALS("BAD_CREDENTIALS","Username and / password not match ",UNAUTHORIZED),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND","Username not found",NOT_FOUND),
    ERR_INTERNAL_SERVER_ERROR("ERR_INTERNAL_SERVER_ERROR","Internal server error",INTERNAL_SERVER_ERROR);


    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(final String code,
              final String defaultMessage,
              final HttpStatus status){
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }
}
