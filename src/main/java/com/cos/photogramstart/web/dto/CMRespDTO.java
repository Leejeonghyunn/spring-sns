package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDTO<T> { //응답의 DTO

    private int code; // 1(성공), -1(실패)
    private String message;
    //private Map<String, String> errorMap;
    private T date;
}
