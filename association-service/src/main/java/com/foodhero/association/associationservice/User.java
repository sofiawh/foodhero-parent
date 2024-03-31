package com.foodhero.association.associationservice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    //private String motDePasse;

    //@OneToMany(mappedBy = "user")
    //@JsonIgnoreProperties("user") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<PreferenceAlimentaire> preferencesAlimentaires;


}
