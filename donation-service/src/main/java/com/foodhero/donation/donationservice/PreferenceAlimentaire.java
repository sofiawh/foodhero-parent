package com.foodhero.donation.donationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreferenceAlimentaire {

    private Long id;
    private String nom;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

    // Getters, setters et autres attributs si nécessaire
}
