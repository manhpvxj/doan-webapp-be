package com.webapp.doan.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {
    Integer id;

    String createdAt;

    Long totalPrice;

    String fullName;

    String phoneNumber;

    String address;

    String status;

    List<DetailInvoiceDto> detailInvoices;
}
