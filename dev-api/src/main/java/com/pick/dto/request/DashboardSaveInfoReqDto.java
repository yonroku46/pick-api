package com.pick.dto.request;

import com.pick.model.ShopInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardSaveInfoReqDto {
    private ShopInfo shop;
    private ShopInfo shopOrigin;
}
