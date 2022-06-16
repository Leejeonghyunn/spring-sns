package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException {

    //객체를 구분할때 쓰는 시리얼번호 - 우리한테 중요하지 않다. JVM한테 중요
    private static final long serialVersionUID=1L;

    //private String message;
    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap){
        super(message);
        //this.message = message;
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }


}
