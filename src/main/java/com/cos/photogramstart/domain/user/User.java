package com.cos.photogramstart.domain.user;

//JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API 제공)

import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity // DB에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다.
    private int id; //Primary 키

    @Column(length = 20, unique = true) //회원가입 아이디 중복방지
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; //웹 사이트
    private String bio; //자기 소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; //사진

    private String role; //권한
    private LocalDateTime createDate;


    @PrePersist // DB에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
