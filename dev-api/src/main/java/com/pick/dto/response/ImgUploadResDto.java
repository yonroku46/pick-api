package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ImgUploadResDto extends ResponseData {
    private String imgPath;
    private Boolean result;

    public ImgUploadResDto(String imgPath, Boolean result) {
        this.imgPath = (String) imgPath;
        this.result = (Boolean) result;
    }
}
