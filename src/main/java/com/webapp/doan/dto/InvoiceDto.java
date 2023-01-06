package com.webapp.doan.dto;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {

    String createdAt;

    String updatedAt;

    Long totalPrice;

    String fullName;

    String phoneNumber;

    String address;

    String status;

    List<DetailInvoiceDto> detailInvoices;
}
