package com.pick.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListResponse<T> extends CommonResponse {
    List<T> dataList;
}
