package com.cos.photogramstart.service;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.exception.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(int fromUserId, int toUserId) {

        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독하였습니다.");
        }

    }

    @Transactional
    public void 구독취소하기(int fromUserId, int toUserId) { //오류가 날 일이 없다

        subscribeRepository.mUnSubscribe(fromUserId, toUserId);

    }

    @Transactional
    public List<SubscribeDto> 구독리스트(int principalId, int pageUserId) {



        return null;
    }
}
