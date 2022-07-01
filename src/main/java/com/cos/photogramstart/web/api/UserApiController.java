package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.exception.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDTO;
import com.cos.photogramstart.web.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController //데이터를 응답할것이기 때문
public class UserApiController {

    private final UserService userService; //DI 주입

    @PutMapping("/api/user/{id}")
    public CMRespDTO<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult,     //꼭 Vaild가 적혀있는 다음 파라미터에 적어야함 !!!!
                               @AuthenticationPrincipal PrincipalDetails principalDetails) { //세션정보 변경

        if (bindingResult.hasErrors()) {     //오류가 발생하면 getFieldErrors() 컬렉션에 모아준다
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {

                errorMap.put(error.getField(), error.getDefaultMessage()); //20이하여야 합니다
            }
            throw new CustomValidationApiException("유효성 검사 실패", errorMap); //예외 발생시킴
        } else {

            User userEntity = userService.회원수정(id, userUpdateDto.toEntity()); //userObject를 날린다
            principalDetails.setUser(userEntity); //세션정보 변경

            return new CMRespDTO<>(1, "회원수정 완료", userEntity); //응답의 DTO. 1은 성공
            //응답시에 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱하여 응답한다.
        }
    }
}