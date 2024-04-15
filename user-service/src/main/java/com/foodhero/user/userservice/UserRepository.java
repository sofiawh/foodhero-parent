package com.foodhero.user.userservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByidKeyCloak(Long idKeycloak);
    // Ajoutez des méthodes spécifiques si nécessaire
}

