package com.pick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Tuple;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetail {
    private Integer designer;
    private Integer style;
    private Integer customers;
    private Integer discount;
    private List<Order> orders;
}
