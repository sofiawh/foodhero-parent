package com.foodhero.user.userservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;






@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donation {

    private Long id;
    private double montant;
    private String typeDon;
    private Long userId;
    private Long associationId;
    private String status;// a faire comme enum
    private Long annonceId;

}

