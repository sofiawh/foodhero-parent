package com.foodhero.enrollment.client;

import com.foodhero.enrollment.User;
import com.foodhero.enrollment.interceptors.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service d'étudiants


///////////////////
@FeignClient(name= "user-service", url = "${application.config.users-url}")

public interface UserFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id);
}
