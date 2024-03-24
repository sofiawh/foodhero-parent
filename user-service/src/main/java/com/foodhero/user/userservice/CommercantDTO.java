package com.foodhero.user.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommercantDTO {

    private String nomCommerce;
    private String description;
    private Long idUser;

    // Getters et setters
}

