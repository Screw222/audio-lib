/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.entites;

/**
 * Audio Entity
 *
 * @author Victor Novikov
 */
public class Audio {

    private int id;
    private String name;
    private String author;
    private String album;
    private int year;
    private String url;

    public Audio() {
        super();
    }

    public Audio(int id, String name, String author, String album, int year, String url) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.album = album;
        this.year = year;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", author=" + author
                + ", album=" + album + ", year=" + year + ", url=" + url + ']';
    }

}
