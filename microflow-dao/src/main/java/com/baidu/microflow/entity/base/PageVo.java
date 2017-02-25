package com.baidu.microflow.entity.base;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageVo implements Pageable {

    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort sort;

    public PageVo() {
    }

    public PageVo(int pageNumber, int pageSize, Sort sort) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getOffset() {
        return pageNumber * pageSize;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return new PageVo(getPageNumber() - 1, getPageSize(), getSort());
    }

    @Override
    public Pageable next() {
        return new PageVo(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pageable first() {
        return new PageVo(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }
}
