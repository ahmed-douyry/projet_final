package com.example.projet_final.repositories;

import com.example.projet_final.dtos.CustmerDto;
import com.example.projet_final.entities.Custmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Custmer  , Long> {
    List<Custmer> findByNameContains(String name);

}
