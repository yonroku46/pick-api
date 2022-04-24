package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BooleanResDto extends ResponseData {
    private Boolean result;

    public BooleanResDto(Boolean result) {
        this.result = (Boolean) result;
    }
}
