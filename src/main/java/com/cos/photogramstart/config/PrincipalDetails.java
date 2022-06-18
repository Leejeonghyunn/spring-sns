package com.cos.photogramstart.config;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID=1L;

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //권한 : 한개가 아닐 수 있음.(3개 이상의 권한)
    //권한을 가져오는 메서드 (user의 role)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //권한을 가져오는 함수 - 컬렉션으로 들어와야 한다

        //GrantedAuthority 타입으로 받아주기
        Collection<GrantedAuthority> collector = new ArrayList<>();
        //비어있는 권한을 부여해주기
        collector.add(() -> user.getRole());

        return collector;
    }

    //user정버를 가져오는 Getter
    @Override //user의 password를 가져오는 메서드
    public String getPassword() {
        return user.getPassword();
    }

    @Override //user의 username을 가져오는 메서드
    public String getUsername() {
        return user.getUsername();
    }

    //return이 true일때, 정상적으로 '로그인 로직'이 실행됨
    @Override //계정이 만료되지 않았는가?
    public boolean isAccountNonExpired() {
        return true; //응 만료되지 않았어
    }

    @Override //계정이 잠기지 않았는가?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //비밀번호 변경한지 오래되지 않았는가?
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정이 활성화 되어있는가?
    public boolean isEnabled() {
        return true;
    }
}
