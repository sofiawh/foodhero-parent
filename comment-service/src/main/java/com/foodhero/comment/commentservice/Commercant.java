package com.foodhero.comment.commentservice;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commercant {

    private String nomCommerce;
    private String description;
    private String adresse;


    //@JsonIgnoreProperties("commercant") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<Annonce> annonces;


}
