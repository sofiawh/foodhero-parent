package com.foodhero.user.userservice;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}

