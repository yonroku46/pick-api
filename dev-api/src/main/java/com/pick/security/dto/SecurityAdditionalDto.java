package com.pick.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class SecurityAdditionalDto {
    private String info;
    private String career;
    private String employment;
    private String policy;

    public SecurityAdditionalDto() {
        this.info = null;
        this.career = null;
        this.employment = null;
        this.policy = null;
    }
}
