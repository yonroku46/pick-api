package com.pick.dto.base;

import javax.persistence.Tuple;

public class ResponseData {

    public String convertSerial(String serial) {
        String res = null;
        if (serial.contains("HS")) {
            res = "hairshop";
        } else if (serial.contains("RT")) {
            res = "restaurant";
        } else if (serial.contains("CF")) {
            res = "cafe";
        }
        return res;
    }

}
