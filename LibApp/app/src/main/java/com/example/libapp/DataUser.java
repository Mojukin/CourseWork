package com.example.libapp;

public class DataUser {

    private int _idUser;
    private String nameUser;
    private String surnameUser;
    private String emailUser;
    private String date_of_birthUser;
    private String passwordUser;


    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurnameUser() {
        return surnameUser;
    }

    public void setSurnameUser(String surnameUser) {
        this.surnameUser = surnameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDate_of_birthUser() {
        return date_of_birthUser;
    }

    public void setDate_of_birthUser(String date_of_birthUser) {
        this.date_of_birthUser = date_of_birthUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
