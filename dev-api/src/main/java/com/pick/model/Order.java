package com.pick.model;

import com.pick.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Menu {
    private Boolean newFlag;
    private Integer num;
}
