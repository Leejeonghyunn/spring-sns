package com.cos.photogramstart.web.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name; //필수
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    //조금 위험함. 코드 수정이 필요할 예정
    public User toEntity() {

        return User.builder()
                .name(name) // name을 기재 안했으면 문제
                .password(password) //password를 사용자가 기재하지 않았으면 문제!! Validation 체크
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
