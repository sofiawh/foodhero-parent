package com.foodhero.association.associationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDTO {

    private Long id;
    private String nom;
    private String email;
    private String localisation;

}

