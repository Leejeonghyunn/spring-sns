package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder //Builder 패턴으로 데이터를 담을 수 있게 해주는 어노테이션
@AllArgsConstructor //모든 생성자를 자동으로 만들어주는 어노테이션
@NoArgsConstructor //빈 생성자를 자동으로 만들어주는 어노테이션
@Data //자동으로 Getter,Setter,toString을 만들어주는 어노테이션
@Entity // DB에 테이블을 생성해주는 어노테이션
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk", //unique 제약조건 이름
                        columnNames = {"fromUserId", "toUserId"} //unique 제작조건을 적용할 실제 데이터베이스의 컬럼명
                )
        }
)
public class Subscribe { //중간 테이블

    @Id //PK키를 지정해주는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private int id; //데이터가 들어갈떄마다 번호를 매겨줄것임

    @JoinColumn(name ="fromUserId") //이렇게 컬럼명 만들어라
    @ManyToOne
    private User fromUser; //구독자

    @JoinColumn(name="toUserId")
    @ManyToOne
    private User toUser; //피구독자
    private LocalDateTime createDate; //데이터가 입력된 시간

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
