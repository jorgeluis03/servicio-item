package com.example.servicioitem.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer id;
    private String productName;
    private Supplier supplier;
    private Category category;
    private String quantityPerUnit;
    private Double unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;
    private Boolean discontinued;
    private int port;
}
