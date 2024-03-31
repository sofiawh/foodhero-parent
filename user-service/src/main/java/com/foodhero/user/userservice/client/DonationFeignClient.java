package com.foodhero.user.userservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Déclaration de l'interface Feign pour le service de commercant

@FeignClient(name= "enrollmentClient", url = "${application.config.enrollments-url}")
public interface DonationFeignClient {
    @GetMapping("/commercants/{id}/users")
    ResponseEntity<List<Long>> getEnrolledCommercantsByUserId(@PathVariable Long id);
   // ResponseEntity<Course> getCourseById(@PathVariable Integer id);


}
