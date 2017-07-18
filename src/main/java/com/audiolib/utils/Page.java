/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.utils;

import java.util.List;

/**
 * Container for Entities
 *
 * @author Victor Novikov
 */
public class Page {

    List<?> content;
    boolean last;
    boolean first;
    int totalElements;
    int totalPages;
    int size;
    int number;

    /**
     *
     * @param content entity list
     * @param size    size of pages
     * @param number  number of pages
     * @param total   page count
     */
    public Page(List<?> content, int size, int number, int total) {
        this.content = content;
        this.size = size;
        this.number = number;
        this.totalElements = total;
        if (size * number >= total) {
            this.last = true;
        }
        if (number == 1) {
            this.first = true;
        }
        int temp = totalElements / size;
        if (totalElements % size == 0) {
            this.totalPages = temp;
        } else {
            this.totalPages = temp + 1;
        }
    }

    public List<?> getContent() {
        return content;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isFirst() {
        return first;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    /**
     *
     * @return JSON String where
     *         content - entity list
     *         last - is page last
     *         first - is page first
     *         totalElements - total entites in Storage
     *         totalPages - total Pages from Storage with current Size and Number of
     *         pages
     *         size - Size of Page
     *         number - Number of Page
     */
    @Override
    public String toString() {
        return "{" + "content=" + content + ", last=" + last + ", first=" + first + ", totalElements=" + totalElements + ", totalPages=" + totalPages + ", size=" + size + ", number=" + number + '}';
    }

}
