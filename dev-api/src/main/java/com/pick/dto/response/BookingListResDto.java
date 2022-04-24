package com.pick.dto.response;

import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListResDto extends ResponseData {
    private Integer bookingCd;
    private String bookingCategory;
    private Timestamp bookingTime;
    private Integer bookingPrice;
    private String style;
    private String designer;
    private String discount;
    private String customers;
    private String orders;
    private Integer shopCd;
    private String shopName;

    public BookingListResDto(Tuple tuple) {
        this.bookingCd = (Integer) tuple.get(0);
        this.bookingCategory = (String) tuple.get(1);
        this.bookingTime = (Timestamp) tuple.get(2);
        this.bookingPrice = (Integer) tuple.get(3);
        this.style = (String) tuple.get(4);
        this.designer = (String) tuple.get(5);
        this.discount = (String) tuple.get(6);
        this.customers = (String) tuple.get(7);
        this.orders = (String) tuple.get(8);
        this.shopCd = (Integer) tuple.get(9);
        this.shopName = (String) tuple.get(10);
    }
}
