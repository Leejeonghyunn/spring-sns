package com.cos.photogramstart.web.dto;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data //Getter,Setter
public class SignupReqDto { //요청하는 DTO - 통신할때 필요한 데이터를 담아두는 Object (Data Transform Object)

    //https://bamdule.tistory.com/35 (@Valid 어노테이션 종류)
    @Size(min = 2, max = 20) //스프링부트 @Valid
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity() {

        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
