package com.foodhero.merchant.merchantservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommercantDTO {

    private Long id;
    private String nomCommerce;
    private String description;
    private String adresse;
   // private Long idUser;

    // Getters et setters
}

