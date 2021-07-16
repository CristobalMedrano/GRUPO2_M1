package com.example.diplomadosuno.models;

public class Postulant {

    private long id;
    private String name;
    private String email;
    private long id_diplomate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_diplomate(){
        return id_diplomate;
    }

    public void setId_diplomate(long id_diplomate){
        this.id_diplomate = id_diplomate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
