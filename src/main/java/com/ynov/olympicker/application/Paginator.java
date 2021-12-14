package com.ynov.olympicker.application;

import lombok.Data;

@Data
public class Paginator {
    private int page;
    private int nbPage;
    private int nbElement;
    private int nbElementPerPage;

    public Paginator(int page, int nbElement, int nbElementPerPage) {
        this.page = page;
        this.nbElement = nbElement;
        this.nbElementPerPage = nbElementPerPage;
        this.nbPage = (int) Math.ceil((double) nbElement / nbElementPerPage);
    }
}
