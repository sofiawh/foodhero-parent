package com.foodhero.donation.donationservice.client;

import com.foodhero.donation.donationservice.Association;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service de cours

@FeignClient(name= "association-service", url = "${application.config.associations-url}")
public interface AssociationFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Association> getAssociationById(@PathVariable /*Long*/String  id);


}
