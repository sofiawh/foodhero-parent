package com.foodhero.user.userservice.client;


import com.foodhero.user.userservice.Commercant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Déclaration de l'interface Feign pour le service de cours

@FeignClient(name= "merchantClient", url = "${application.config.commercants-url}")
public interface CommercantFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Commercant> getCommercantWithAnnonces(@PathVariable Long id);//getCommercantById(@PathVariable Long id);


}
