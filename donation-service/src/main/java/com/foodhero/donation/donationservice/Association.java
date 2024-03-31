package com.foodhero.donation.donationservice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Association {

    //private String title;
    private String nomCommerce;
    private String description;
    private String adresse;
//    @OneToMany(mappedBy = "commercant")
//    @JsonIgnoreProperties("commercant") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<Association> annonces;


}
