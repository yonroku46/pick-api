package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class BooleanResDto extends ResponseData {
    private Boolean result;

    public BooleanResDto(Boolean result) {
        this.result = (Boolean) result;
    }
}
