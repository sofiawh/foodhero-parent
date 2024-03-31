package com.foodhero.association.associationservice;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donation {

    private double montant;
    private String typeDon;
    private Long userId;
    private Long associationId;
    private String status;// a faire comme enum

}

