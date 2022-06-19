package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User 회원수정(int id, User user) {

        //1.영속화
        //User userEntity = userRepository.findById(id).get(); //1.무조건 찾았다 걱정마 get() 2. 못찾았어 Excetption 발동시킬게
                                                                                       // orElseThrow()
        User userEntity = userRepository.findById(id).orElseThrow(() ->  {
            return new CustomValidationApiException("찾을 수 없는 아이디 입니다."); //생성자 새로 추가
        });

        //2.영속화된 오브젝트를 수정 - DirtyChecking (업데이트 완료)

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); //password 암호화를 해주어야 한다

        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity; //DirtyChecking이 일어나서 업데이트가 완료경
    }
}
//영속화된 Object를 수정하면 자동으로 DB에 반영이 된다
