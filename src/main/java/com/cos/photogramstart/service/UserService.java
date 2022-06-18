package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User 회원수정(int id, User user) {

        //1. 영속화
//        User userEntity = userRepository.findById(i        User userEntity = userRepository.findById(id)
//                .orElseThrow(() -> {
//                    throw new UsernameNotFoundException(id + ": 존재하지 않습니다.");
//                });d).get();t(), 2.못찾았어 Exception 발동시킬게 or Else Throw()
        User userEntity = userRepository.findById(id).get();
        //2. 영속화된 오브젝트를 수 //1.무조건 찾았다 걱정마 ge정 - dirtyChecking(업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        if (rawPassword == null || rawPassword.length() < 5) {
        }else {
            String encPassword = bCryptPasswordEncoder.encode(rawPassword);
            userEntity.setPassword(encPassword); //
        }

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }

}
