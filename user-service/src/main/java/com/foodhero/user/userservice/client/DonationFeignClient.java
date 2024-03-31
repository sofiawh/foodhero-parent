package com.foodhero.user.userservice.client;


import com.foodhero.user.userservice.Donation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

// Déclaration de l'interface Feign pour le service d association

@FeignClient(name= "donationClient", url = "${application.config.donations-url}")
public interface DonationFeignClient {
    @GetMapping("/associations/{id}/users")
    ResponseEntity<List<Long>> getDonatedAssociationsByUserId(@PathVariable Long id);
    @GetMapping("/users/{userId}/dons")
    Optional<List<Donation>> getDonsByUserId(@PathVariable Long userId);
    // ResponseEntity<Course> getCourseById(@PathVariable Integer id);


}
