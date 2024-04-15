package com.foodhero.merchant.merchantservice;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commercants")
public class CommercantEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private /*Long*/String id;
    private Long idKeyCloak;
    private String nomCommerce;
    private String description;
    private String adresse;

//    @OneToOne
//    @JoinColumn(name = "id_utilisateur")
//    private UtilisateurEntity utilisateur;

    @OneToMany(mappedBy = "commercant")
    @JsonIgnoreProperties("commercant") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<Annonce> annonces;

    // Getters, setters et autres attributs si nécessaire
}
