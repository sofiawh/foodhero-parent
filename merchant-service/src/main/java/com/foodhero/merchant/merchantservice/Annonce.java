package com.foodhero.merchant.merchantservice;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "annonces")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Long prix;
    private Long ratings;
    private String categorie;
//    @ElementCollection
//    private List<String> image;
    private String image;
    private Long revenue;
    private Long ordersDonation;
    private String message;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "commercant_id")
    //@JsonIgnoreProperties("annonce") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private CommercantEntity commercant;

    // Getters, setters et autres attributs si nécessaire
}

