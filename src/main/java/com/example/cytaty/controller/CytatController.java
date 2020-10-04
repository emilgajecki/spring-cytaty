package com.example.cytaty.controller;

import com.example.cytaty.model.Cytat;
import com.example.cytaty.repository.CytatReposiotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class CytatController {

    //do pola repozytorium wstrzyknie bean/obiekt istniejace repozytorium
    @Autowired
    CytatReposiotory reposiotory;

    // pobieranie danych z bazy
    @GetMapping("/cytaty")
    public List<Cytat> wszystkieCytaty(){
        return reposiotory.findAll();
    }

    //odpowiada za wprowadzenie danych - np wprowadzanie danych z formularza
    @PostMapping("/cytaty")
                            //odpowiada za przekazywanie parametrow
    public String dodajCytat(@RequestBody Cytat cytat){
        reposiotory.save(cytat);
        return "Dodałem cytat " + cytat.getCytat();
    }

    //usuwanie danego wpisu w baze
    @DeleteMapping("/cytaty")
    public String usunCytat(long id){
        reposiotory.deleteById(id);
        return "usunięto rekord "+id;
    }

    //sposób na wywołanie metody zaraz po kontrolerze.
    //@PostConstruct
    public void zrobPareCytatow(){
        Cytat cytat1 = new Cytat("Tomasz Nowak","Kasa kasa kasa");
        Cytat cytat2 = new Cytat("Jan Stary","Pieniądze to nie wszystko");
        reposiotory.save(cytat1);
        reposiotory.save(cytat2);
    }



}
