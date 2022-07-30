package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import com.pick.model.ShopMenu;
import com.pick.model.ShopStaff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class DashboardRequestListResDto extends ResponseData {
    private Integer requestCd;
    private Timestamp requestTime;
    private Integer userCd;
    private String userName;
    private String userEmail;
    private String userImg;

    public DashboardRequestListResDto(Tuple tuple) {
        this.requestCd = (Integer) tuple.get(0);
        this.requestTime = (Timestamp) tuple.get(1);
        this.userCd = (Integer) tuple.get(2);
        this.userName = (String) tuple.get(3);
        this.userEmail = (String) tuple.get(4);
        this.userImg = (String) tuple.get(5);
    }

}
