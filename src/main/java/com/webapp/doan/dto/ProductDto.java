package com.webapp.doan.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    Integer id;
    String name;
    String description;
    Integer priceSell;
    Integer quantity;
    List<String> image;
    Integer categoryId;

}
