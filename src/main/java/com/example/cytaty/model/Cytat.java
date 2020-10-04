package com.example.cytaty.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//encja
@Entity
public class Cytat {

    @Id
    @GeneratedValue
    private long id;
    private String autor;
    private String cytat;

    public Cytat() {

    }

    public Cytat(String autor, String cytat) {
        this.autor = autor;
        this.cytat = cytat;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCytat() {
        return cytat;
    }

    public void setCytat(String cytat) {
        this.cytat = cytat;
    }

}
