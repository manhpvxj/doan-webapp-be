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
    String cover;
    List<String> images;
    Integer categoryId;

}
