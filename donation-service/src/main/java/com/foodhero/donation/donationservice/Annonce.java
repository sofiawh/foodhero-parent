package com.foodhero.donation.donationservice;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Annonce {

    private String titre;
    private String description;

    //@JsonIgnoreProperties("annonce") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private Commercant commercant;


}

