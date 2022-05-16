package com.pick.security.bean;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class RoleConverter {
    private final Map<Integer, String> role;

    public RoleConverter() {
        // 하위 숫자를 포함하는 계층구조.
        this.role = new HashMap<>();
        role.put(1, "ROLE_NORMAL");
        role.put(2, "ROLE_STAFF");
        role.put(3, "ROLE_MANAGER");
        role.put(9, "ROLE_SYSTEMADMIN");
    }

    public String convertToString(Integer roleInt) {
        if (this.role.containsKey(roleInt)) {
            return this.role.get(roleInt);
        }
        return null;
    }

    public Integer convertToInteger(String roleStr) {
        for (Map.Entry<Integer, String> entry : this.role.entrySet()) {
            if (entry.getValue().equals(roleStr)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Set<Integer> getKeys() {
        return role.keySet();
    }
}