package com.webapp.doan.dto;

import lombok.Data;

@Data
public class PageDto {
    int total;

    int currentPage;

    int size;

    public PageDto(int total, int page, int size) {
        this.total = total;
        this.currentPage = page;
        this.size = size;
    }
}
