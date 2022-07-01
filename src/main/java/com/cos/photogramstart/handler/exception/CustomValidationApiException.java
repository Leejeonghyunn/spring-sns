package com.cos.photogramstart.handler.exception;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

    //객체를 구분할 떄!!!!
    private Map<String, String> errorMap;

    public CustomValidationApiException(String message) { //String만 받는 생성자 새로 추가
        super(message);
    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap =errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}
