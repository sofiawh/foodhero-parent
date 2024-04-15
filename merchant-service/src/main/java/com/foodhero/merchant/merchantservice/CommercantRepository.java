package com.foodhero.merchant.merchantservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercantRepository extends JpaRepository<CommercantEntity, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
    CommercantEntity findByIdKeyCloak(Long idKeyCloak);

}

