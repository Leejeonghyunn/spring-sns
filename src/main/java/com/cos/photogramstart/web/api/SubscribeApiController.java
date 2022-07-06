package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    //구독하기
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable int toUserId) {
        subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new CMRespDTO<>(1,"구독하기 성공", null ), HttpStatus.OK);
    }

    //구독취소하기
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @PathVariable int toUserId) {

        subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new CMRespDTO<>(1,"구독 취소하기 성공", null ), HttpStatus.OK);

    }

}