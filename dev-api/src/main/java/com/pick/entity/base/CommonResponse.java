package com.pick.entity.base;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponse {
    boolean success;
    int conde;
    String message;
}
