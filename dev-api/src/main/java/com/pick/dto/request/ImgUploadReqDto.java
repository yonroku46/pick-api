package com.pick.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ImgUploadReqDto {
    private MultipartFile file;
    private Integer userCd;
    private Integer shopCd;
    private Integer menuCd;
    private String call;
}
