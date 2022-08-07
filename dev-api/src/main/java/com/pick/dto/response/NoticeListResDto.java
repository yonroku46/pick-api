package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import com.pick.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class NoticeListResDto extends ResponseData {
    private List<Notice> noticeList;
}
