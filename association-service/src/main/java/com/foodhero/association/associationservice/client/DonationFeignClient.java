package com.foodhero.association.associationservice.client;


import com.foodhero.association.associationservice.Donation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

// Déclaration de l'interface Feign pour le service d association

@FeignClient(name= "donation", url = "${application.config.donations-url}")
public interface DonationFeignClient {
    @GetMapping("/associations/{id}/users")
    ResponseEntity<List<Long>> getDonateddUsersByAssociationId(@PathVariable Long id);
   // ResponseEntity<Course> getCourseById(@PathVariable Integer id);
   @GetMapping("/associations/{associationId}/dons")
   Optional<List<Donation>> getDonsByAssociationId(@PathVariable Long associationId);
    // ResponseEntity<Course> getCourseById(@PathVariable Integer id);



}
