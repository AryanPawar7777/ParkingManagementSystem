package com.parking.users.dto;

import java.util.List;

public class PaginatedResponse<T> {
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> data;

    public PaginatedResponse(int page, int size, int totalPages, long totalElements, List<T> data) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.data = data;
    }

    // Getters and Setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }
    public List<T> getData() { return data; }
    public void setData(List<T> data) { this.data = data; }
}