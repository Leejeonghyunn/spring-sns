package com.cos.photogramstart.handler.exception;

public class CustomException extends RuntimeException {

    //객체를 구분할때 쓰는 시리얼번호 - 우리한테 중요하지 않다. JVM한테 중요
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);

    }
}

