package com.connectJPA.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key" , HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed",  HttpStatus.BAD_REQUEST),
    DISH_EXISTED(2002, "Dish existed", HttpStatus.BAD_REQUEST),
    DRINKS_EXISTED(3002, "Drinks existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} character", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} character", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed" ,HttpStatus.NOT_FOUND),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    DISH_NOT_EXISTED(2005, "Dish not existed", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(3005,"Product not found",HttpStatus.NOT_FOUND),
    DRINKS_NOT_EXISTED(3005, "Drinks not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN)

    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
