package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.exception.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.SignupReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor //final이 있는 코드에 대한 '생성자'를 만들어준다 - DI할때 사용
@Controller // 1. IOC에 등록이 되었다. 2. 파일을 return 하는 컨트롤러가 되었다

public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;


    @GetMapping("auth/signin")
    public String signinForm() {

        return "auth/signin";
    }

    @GetMapping("auth/signup")
    public String signupForm() {

        return "auth/signup";
    }

    //회원가입 버튼 -> /auth/signup -> /auth/signin
    @PostMapping("auth/signup")
    public String signup(@Valid SignupReqDto signupReqDto, BindingResult bindingResult) {
        //회원가입 진행 : key=value (x-www-form-urlencoded)

        if (bindingResult.hasErrors()) {     //오류가 발생하면 getFieldErrors() 컬렉션에 모아준다
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {

                errorMap.put(error.getField(), error.getDefaultMessage()); //20이하여야 합니다
                System.out.println("===================");
                System.out.println(error.getDefaultMessage());
                System.out.println("===================");
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap); //예외 발생시킴
        } else {

            //User <- SignupReqDto
            User user = signupReqDto.toEntity();

            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);

            return "auth/signin";   //회원가입 성공하면 signin 페이지로 이동한다

        }
        //@ResponseBody 가 return 타입앞에 붙어있으면 '데이터'를 응답한다
    }
}

