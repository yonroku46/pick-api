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
public class BookingInfoResDto extends ResponseData {
    private Integer bookingCd;
    private Integer userCd;
    private Integer shopCd;
    private Integer managerCd;
    private String bookingCategory;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp bookingTime;
    private String bookingDetail;
    private Integer bookingPrice;
    private Integer bookingStat;
    private String style;
    private String designer;
    private String discount;
    private String customers;
    private String orders;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    public BookingInfoResDto(Tuple tuple) {
        this.bookingCd = (Integer) tuple.get(0);
        this.userCd = (Integer) tuple.get(1);
        this.shopCd = (Integer) tuple.get(2);
        this.managerCd = (Integer) tuple.get(3);
        this.bookingCategory = (String) tuple.get(4);
        this.bookingTime = (Timestamp) tuple.get(5);
        this.bookingDetail = (String) tuple.get(6);
        this.bookingPrice = (Integer) tuple.get(7);
        this.bookingStat = (Integer) tuple.get(8);
        this.style = (String) tuple.get(9);
        this.designer = (String) tuple.get(10);
        this.discount = (String) tuple.get(11);
        this.customers = (String) tuple.get(12);
        this.orders = (String) tuple.get(13);
        this.createTime = (Timestamp) tuple.get(14);
    }
}
