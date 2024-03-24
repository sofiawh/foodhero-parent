package com.foodhero.merchant.merchantservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Déclaration de l'interface Feign pour le service de commercant

@FeignClient(name= "enrollment", url = "${application.config.enrollments-url}")
public interface EnrollmentFeignClient {
    @GetMapping("/commercants/{id}/users")
    ResponseEntity<List<Long>> getEnrolledUsersByCommercantId(@PathVariable Long id);
   // ResponseEntity<Course> getCourseById(@PathVariable Integer id);


}
