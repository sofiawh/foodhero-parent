package com.foodhero.merchant.merchantservice;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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

    @ManyToOne
    @JoinColumn(name = "commercant_id")
    //@JsonIgnoreProperties("annonce") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private CommercantEntity commercant;

    // Getters, setters et autres attributs si nécessaire
}

