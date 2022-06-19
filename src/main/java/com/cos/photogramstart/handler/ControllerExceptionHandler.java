package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice //모든 Exception을 다 낚아챈다
public class ControllerExceptionHandler {

    //JavaScript로 응답하는 Handler
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e) {

        //CMRespDto, Script 비교
        //1. 클라이언트에게 응답할때는 Script 좋음
        //2. Ajax통신을 하거나 Android 통신을 하게되면 CMRespDto가 좋다
        //즉, 개발자를 위한 응답에는 CMRespDto, 클라이언트를 위해서는 Script가 좋다

        return Script.back(e.getErrorMap().toString());

        //자바스크립트로 짜는 부분까지 2가지 방향으로 갔을때 사용자에게 어떤것이 좋을지 판단해보라고 나눈것
    }

    //CMRespDto 오브젝트를 응답하는 핸들러
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMRespDTO<?>> validationApiException(CustomValidationApiException e) {

        System.out.println("=======================나 실행됨?");

        return new ResponseEntity<>(
                new CMRespDTO<>(-1, e.getMessage(), e.getErrorMap()),
                HttpStatus.BAD_REQUEST
        );

    }
    // <?>를 사용하면 제네릭 타입이 결정이 된다
    // BAD_REQUEST는 400번대 오류이다 -> 너가 요청을 잘못했다
}
