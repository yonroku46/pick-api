package com.pick.security.bean;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class RoleConverter {
    private final Map<Integer, String> role;

    // 하위 숫자를 포함하는 계층구조.
    public RoleConverter() {
        this.role = new HashMap<>();
        role.put(1, "ROLE_NORMAL");
        role.put(2, "ROLE_STAFF");
        role.put(3, "ROLE_MANAGER");
        role.put(9, "ROLE_SYSTEMADMIN");
    }

    public String convertToString(Integer roleKey) {
        if (this.role.containsKey(roleKey)) {
            return this.role.get(roleKey);
        }
        return null;
    }

    public Integer convertToInteger(String roleValue) {
        for (Map.Entry<Integer, String> entry : this.role.entrySet()) {
            if (entry.getValue().equals(roleValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Set<Integer> getKeys() {
        return role.keySet();
    }
}