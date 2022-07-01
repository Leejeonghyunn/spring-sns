package com.cos.photogramstart.web;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.handler.exception.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() { //기본페이지
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() { //인기페이지
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() { //사진 등록을 위한 페이지
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails) {

        //서비스 호출
        imageService.사진업로드(imageUploadDto, principalDetails);

        if (imageUploadDto.getFile().isEmpty()) { // imageUploadDto에서 File이 없으면
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        return "redirect:/user/"+principalDetails.getUser().getId();

    }

}
