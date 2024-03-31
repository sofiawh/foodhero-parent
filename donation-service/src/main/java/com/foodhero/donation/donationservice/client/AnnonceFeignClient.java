package com.foodhero.donation.donationservice.client;

import com.foodhero.donation.donationservice.Annonce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service association

@FeignClient(name= "annonce-service", url = "${application.config.annonces-url}")

public interface AnnonceFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Annonce> getAnnonceById(@PathVariable Long id);
}
