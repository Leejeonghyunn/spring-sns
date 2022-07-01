package com.cos.photogramstart.web.dto.Image;

import com.cos.photogramstart.domain.Image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {

    private MultipartFile file; //@NotBlank 처리가 안된다.
    private String caption;

    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                //.postImageUrl(file.getOriginalFilename())
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }

}
