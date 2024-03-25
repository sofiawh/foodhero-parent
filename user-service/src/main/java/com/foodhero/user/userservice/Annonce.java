package com.foodhero.user.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Annonce {

    private Long id;
    private String titre;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "commercant_id")
    //@JsonIgnoreProperties("annonce") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private Commercant commercant;

}


