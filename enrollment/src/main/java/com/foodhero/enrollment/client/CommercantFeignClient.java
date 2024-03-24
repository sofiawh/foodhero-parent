package com.foodhero.enrollment.client;

import com.foodhero.enrollment.Commercant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service de cours

@FeignClient(name= "merchant-service", url = "${application.config.commercants-url}")
public interface CommercantFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Commercant> getCommercantById(@PathVariable Long id);


}
