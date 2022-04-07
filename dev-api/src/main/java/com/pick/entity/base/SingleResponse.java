package com.pick.entity.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResponse<T> extends CommonResponse {
    T data;
}
