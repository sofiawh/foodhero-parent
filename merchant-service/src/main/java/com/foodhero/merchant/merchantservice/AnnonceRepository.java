package com.foodhero.merchant.merchantservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}

