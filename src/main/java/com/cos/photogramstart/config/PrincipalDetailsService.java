package com.cos.photogramstart.config;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //IOC
public class PrincipalDetailsService implements UserDetailsService { //부모타입이 서로 같다

    private final UserRepository userRepository;

    //1. password는 알아서 체킹하니까 신경쓸 필요가 없다
    //2. return이 잘 되면 자동으로 UserDetails 타입을 세션으로 만들어준다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) { //username을 찾지 못하였다면
            return null; //null을 리턴하고
        } else { // username을 찾았다면
            return new PrincipalDetails(userEntity); //principalDetails를 session에 저정한다
        }
    } //패스워드는 시큐리티가 확인해주기 때문에 신경쓸 필요없다.
}
