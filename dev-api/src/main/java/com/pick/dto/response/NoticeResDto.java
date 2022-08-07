package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class NoticeResDto extends ResponseData {
    private String category;
    private String title;
    private String content;
}
