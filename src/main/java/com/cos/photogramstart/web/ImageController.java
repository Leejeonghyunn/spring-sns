package com.cos.photogramstart.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

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

}
