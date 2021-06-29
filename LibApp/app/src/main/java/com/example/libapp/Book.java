package com.example.libapp;

public class Book {
    private int _idbook;
    private int _idauthor;
    private String name;
    private String oathimagebook;
    private int censure;
    private int isauthoriz;
    private String pathontext;
    private int _new;
    private int popular;


    public Book(int _idbook, int _idauthor, String name, String oathimagebook, int censure, int isauthoriz, String pathontext, int _new, int popular) {
        this._idbook = _idbook;
        this._idauthor = _idauthor;
        this.name = name;
        this.oathimagebook = oathimagebook;
        this.censure = censure;
        this.isauthoriz = isauthoriz;
        this.pathontext = pathontext;
        this._new = _new;
        this.popular = popular;
    }

    public int get_idbook() {
        return _idbook;
    }

    public void set_idbook(int _idbook) {
        this._idbook = _idbook;
    }

    public int get_idauthor() {
        return _idauthor;
    }

    public void set_idauthor(int _idauthor) {
        this._idauthor = _idauthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOathimagebook() {
        return oathimagebook;
    }

    public void setOathimagebook(String oathimagebook) {
        this.oathimagebook = oathimagebook;
    }

    public int getCensure() {
        return censure;
    }

    public void setCensure(int censure) {
        this.censure = censure;
    }

    public int getIsauthoriz() {
        return isauthoriz;
    }

    public void setIsauthoriz(int isauthoriz) {
        this.isauthoriz = isauthoriz;
    }

    public String getPathontext() {
        return pathontext;
    }

    public void setPathontext(String pathontext) {
        this.pathontext = pathontext;
    }

    public int get_new() {
        return _new;
    }

    public void set_new(int _new) {
        this._new = _new;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

}
