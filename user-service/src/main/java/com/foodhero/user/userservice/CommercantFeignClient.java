package com.foodhero.user.userservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "merchant-service")
public interface CommercantFeignClient {

    @GetMapping("/commercants/{id}")
    CommercantDTO getCommercantById(@PathVariable Long id);
}
