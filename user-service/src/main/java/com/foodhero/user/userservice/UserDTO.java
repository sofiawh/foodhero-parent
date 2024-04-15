package com.foodhero.user.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private Long idKeyCloak;
    private String nom;
    private String prenom;
    private String email;

    // Getters et setters
}
