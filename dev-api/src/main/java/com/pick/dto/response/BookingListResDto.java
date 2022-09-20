package com.pick.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pick.dto.base.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class BookingListResDto extends ResponseData {
    private Integer bookingCd;
    private String bookingCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp bookingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp bookingEndTime;
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
        this.bookingEndTime = (Timestamp) tuple.get(3);
        this.bookingPrice = (Integer) tuple.get(4);
        this.style = (String) tuple.get(5);
        this.designer = (String) tuple.get(6);
        this.discount = (String) tuple.get(7);
        this.customers = (String) tuple.get(8);
        this.orders = (String) tuple.get(9);
        this.shopCd = (Integer) tuple.get(10);
        this.shopName = (String) tuple.get(11);
    }
}
