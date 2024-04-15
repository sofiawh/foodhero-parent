package com.foodhero.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    //private String motDePasse;

//    @OneToMany(mappedBy = "user")
//    @JsonIgnoreProperties("user") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<PreferenceAlimentaire> preferencesAlimentaires;

}



