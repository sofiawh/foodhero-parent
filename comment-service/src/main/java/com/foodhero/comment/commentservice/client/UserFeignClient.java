package com.foodhero.comment.commentservice.client;

import com.foodhero.comment.commentservice.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service association

@FeignClient(name= "user-service", url = "${application.config.users-url}")

public interface UserFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id);
}
