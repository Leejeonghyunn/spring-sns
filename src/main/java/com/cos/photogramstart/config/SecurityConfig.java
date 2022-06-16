package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //해당 파일로 시큐리티를 활성화 시키는 어노테이션
@Configuration //IOC컨테이너를 넣어주는 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.

        http.csrf().disable(); // CSRF 토큰 비활성화

        http.authorizeRequests() //이 주소 경로로 요청이 들어오면
                .antMatchers("/", "/user/**", "image/**", "/subscribe/**", "/comment/**")
                .authenticated() //인증이 필요하다
                .anyRequest() //그 외의 요청들은
                .permitAll() //모두 허용한다
                .and() //그리고
                .formLogin() //로그인(인증)이 필요한 요청이 들어오면
                .loginPage("/auth/signin") // (GET요청) 로그인 페이지 auth/signin 으로 이동시키고
                .loginProcessingUrl("/auth/signin") //auth/signin 이라는 POST요청을 실행시킨다
                .defaultSuccessUrl("/"); //인증이 정상적으로 완료되면 "/"으로 이동한다

        //특정한 주소들은 인증이 필요한 페이지가 오면 formLogin을 한다. formLogin이 필요한 페이지가 /auth/signin 이다
        //로그인을 정상적으로 처리하면 "/"로 가게한다 - 302 리다이렉트가 호출된 - 요청 재분배
    }
}
