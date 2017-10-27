package com.thaontm.project.Seconds.utils;

public class PaginationUtils {
    public static int calculateTotalPages(int totalRows, int pageSize) {
        if (totalRows < pageSize) return 1;
        if (totalRows % pageSize != 0) {
            return totalRows / pageSize + 1;
        }
        return totalRows / pageSize;
    }
}
