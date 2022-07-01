package com.cos.photogramstart.service;

import com.cos.photogramstart.config.PrincipalDetails;
import com.cos.photogramstart.domain.Image.Image;
import com.cos.photogramstart.domain.Image.ImageRepository;
import com.cos.photogramstart.web.dto.Image.ImageUploadDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    //6. 이미지가 저장되는 경로 호출하기
    @Value("${file.path}")
    private String uploadFolder = "/Users/jeonghyunlee/Desktop/EaszUp-Springboot-Photogram-Start/upload/";

    @Transactional
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {

        //2. UUID 객체를 생성한다
        UUID uuid = UUID.randomUUID(); //범용 고유 식별자

        //1. 업로드 되는 원본 파일명을 imageFileName이라고 지정한다.
        //3. UUID를 더한 값으로 지정한다
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); //1.jpg

        //4. UUID가 적용된 파일명 확인하기
        System.out.println("이미지 파일 이름 : " + imageFileName);

        //5. Image 저장 경로 확인하기
        Path imageFilePath = Paths.get(uploadFolder + imageFileName); //이미지경로

        //7. 파일을 업로드하기
        //통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //8. Image 파일경로를 DB에 INSERT하기. 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // 이미지 파일 이름
        Image imageEntity = imageRepository.save(image);

        //System.out.println("imageEntity : " + imageEntity.toString());

    }
 }

