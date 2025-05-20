package com.example.teststorebackend.exception;

public class MyException extends RuntimeException {// 그냥 익셉션은 체크드 익셉션이여서 안됨

    public MyException(String message){
        super(message);
    }
}

