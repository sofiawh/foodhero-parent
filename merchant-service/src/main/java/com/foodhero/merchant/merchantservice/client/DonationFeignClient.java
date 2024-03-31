package com.foodhero.merchant.merchantservice.client;


import com.foodhero.merchant.merchantservice.Donation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

// Déclaration de l'interface Feign pour le service d association

@FeignClient(name= "donationClient", url = "${application.config.donations-url}")
public interface DonationFeignClient {
//    @GetMapping("/associations/{id}/users")
//    ResponseEntity<List<Long>> getDonatedAssociationsByUserId(@PathVariable Long id);
    @GetMapping("/annonces/{annonceId}/dons")
    Optional<List<Donation>> getDonsByAnnonceId(@PathVariable Long annonceId);



}
