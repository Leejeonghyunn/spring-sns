package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice //모든 Exception을 다 낚아챈다
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {
        //모든 RuntimeException을 이 함수가 가로챈다

        //return new CMRespDTO<Map<String,String>>(-1, e.getMessage(), e.getErrorMap());
        //미리 return타입을 지정하지 않아도 된다. '추론'이 가능하다

        return Script.back(e.getErrorMap().toString());

    }
    //자바스크립트로 짜는 부분까지 2가지 방향으로 갔을때 사용자에게 어떤것이 좋을지 판단해보라고 나눈것

}
