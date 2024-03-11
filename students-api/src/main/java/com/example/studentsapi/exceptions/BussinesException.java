package com.example.studentsapi.exceptions;

public class BussinesException extends RuntimeException {

    private final int code;

    public BussinesException(int code, String message){
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
