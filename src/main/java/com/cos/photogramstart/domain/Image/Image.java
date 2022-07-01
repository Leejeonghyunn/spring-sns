package com.cos.photogramstart.domain.Image;


import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image { //N:1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; //해당 Image를 설명하는 영역
    private String postImageUrl; //Image을 전송받아서 그 사진을 서버의 특정 폴더에 저장 - DB에 그 저장된 경로를 INSERT

    @JoinColumn(name = "userId")  // 1:1
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; //1명의 User는 여러개의 Image를 만들어낼수 있다. - Image를 누가 올렸는지 알기위해 받은 USerObject

    //이미지 좋아요 기능 (추후에 업데이트)

    //이미지 좋아요 카운팅 (추후에 업데이트)

    //댓글 기능 (추후에 업데이트)
    private LocalDateTime createDate; //모든 DB에는 시간정보가 필요하다 - 데이터가 입력된 시간

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    /**오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User부분을 출력되지 않게 함
     * @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }
    **/
}