package com.foodhero.donation.donationservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
   // private String typeDon;
    private /*Long*/ String userId;
    private /*Long*/ String associationId;
    private String status;// a faire comme enum
    private Long annonceId;
    private LocalDate date;
    private String Pmethod;


}
