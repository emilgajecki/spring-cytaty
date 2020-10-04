package com.example.cytaty.repository;

import com.example.cytaty.model.Cytat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repozytorium
@Repository
// gotowe repo do pracy z baza danych
public interface CytatReposiotory extends JpaRepository<Cytat,Long> {


}
