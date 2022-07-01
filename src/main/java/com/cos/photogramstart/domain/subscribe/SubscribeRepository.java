package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    //구독하기 네이티브 쿼리
    @Modifying   //INSERT, DELETE, UPDATE를 '네이티브 쿼리'로 작성하려면 해당 어노테이션이 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId, now())",nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); //성공하면 1(변경된 행의 개수가 리턴됨)이, 실패하면 -1이 return

    //구독취소하기 네이티브 쿼리
    @Modifying
    @Query(value = "DELETE FORM subscribe WHERE fromUserId = :fromUserId AND toUserId=:toUserId",nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

    //구독상태 확인 네이티브 쿼리
    //@Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId =:principalId AND toUserId", nativeQuery = true)
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :principalId AND toUserId = :pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

    //구독자수 확인 네이티브 쿼리
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);
}
