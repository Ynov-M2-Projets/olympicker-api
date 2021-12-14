package com.ynov.olympicker.application;

import lombok.Data;

import java.util.List;

@Data
public class PaginationResponse<T> {

    private List<T> data;
    private Paginator pageInfo;

    public PaginationResponse(List<T> data, Paginator paginator) {
        this.data = data;
        this.pageInfo = paginator;
    }

}
