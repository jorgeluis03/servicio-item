package com.example.servicioitem.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Supplier {
    private Integer id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String postalCode;
    private String country;
    private String phone;
}
