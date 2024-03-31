package com.foodhero.user.userservice.client;


import com.foodhero.user.userservice.Association;
import com.foodhero.user.userservice.Commercant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service de cours

@FeignClient(name= "associationClient", url = "${application.config.associations-url}")
public interface AssociationFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Association> getAssociationById(@PathVariable Long id);

}
