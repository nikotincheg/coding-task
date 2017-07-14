package com.onikitich.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Product {

    private Integer id;
    private String code;
    private String name;
    private Double price;
    private Offer offer;
}
