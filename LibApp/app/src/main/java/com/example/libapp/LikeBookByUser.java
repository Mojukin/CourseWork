package com.example.libapp;

public class LikeBookByUser {

    public int idLike;
    public int idUser;
    public int idBook;

    public LikeBookByUser(int idLike, int idUser, int idBook) {
        this.idLike = idLike;
        this.idUser = idUser;
        this.idBook = idBook;
    }

    public int getIdLike() {
        return idLike;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }


}
