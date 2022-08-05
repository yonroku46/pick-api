package com.pick.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {
    NORMAL_ROLE("Normal", 1),
    STAFF_ROLE("Staff", 2),
    MANAGER_ROLE("Manager", 3),
    SYSTEM_ADMIN_ROLE("SystemAdmin", 9)
    ;

    private String type;
    private Integer code;

}