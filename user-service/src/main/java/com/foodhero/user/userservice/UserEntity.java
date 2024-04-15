package com.foodhero.user.userservice;

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
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idKeyCloak;
    private String name;
    private String prenom;
    private String email;
    private String motDePasse;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user") // Ignorer la sérialisation de la propriété "commercant" dans la classe Annonce
    private List<PreferenceAlimentaireEntity> preferencesAlimentaires;

}


