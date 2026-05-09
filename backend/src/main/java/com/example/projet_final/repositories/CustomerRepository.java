package com.example.projet_final.repositories;

import com.example.projet_final.entities.Custmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Custmer  , Long> {
}
