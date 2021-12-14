package com.ynov.olympicker.utils;

import com.ynov.olympicker.application.Paginator;

public class PaginationUtils {

    public static Paginator createPaginator(Integer page, Integer size, Integer totalData) {
        return new Paginator(page + 1, totalData, size);
    }

}
