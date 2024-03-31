package com.foodhero.association.associationservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepository extends JpaRepository<AssociationEntity, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}

