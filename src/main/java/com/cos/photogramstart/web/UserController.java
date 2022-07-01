package com.cos.photogramstart.web;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}") //해당 페이지의 주인아이디
    public String profile(@PathVariable int pageUserId, Model model,
                          @AuthenticationPrincipal PrincipalDetails principalDetails) {  //번호를 바꿔도 들어갈 수 있게
        
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails,
                         Model model) {

        //1. 추천
        //System.out.println("세션 정보 :" + principalDetails.getUser());
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //2. 극혐
        //PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
        //System.out.println("직접 찾은 세션 정보 : " + mPrincipalDetails.getUser());

        //model.addAttribute("principal", principalDetails.getUser());

        return "user/update";
    }
}

//Principal은 접근주체(인증주체)로써 인증된 유저의 '오브젝트'로 흔히 사용된다