package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDTO;
import com.cos.photogramstart.web.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController //데이터를 응답할것이기 ㄹ
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDTO<?> update(@PathVariable int id, UserUpdateDto userUpdateDto,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity); //세션정보 변경ㄹ

        return new CMRespDTO<>(1,"회원수정 완료", userEntity);
    }

}