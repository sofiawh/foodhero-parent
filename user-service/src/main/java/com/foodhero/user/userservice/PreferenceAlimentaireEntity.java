package com.foodhero.user.userservice;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "preferenceAlimentaires")
public class PreferenceAlimentaireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Getters, setters et autres attributs si nécessaire
}
