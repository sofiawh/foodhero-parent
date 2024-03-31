package com.foodhero.user.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Association {

    private String nom;
    private String email;
    private String localisation;

}

