package com.foodhero.comment.commentservice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String userName;
    private Long userId;
    private String avatar;
    private String content;
    private Date timestamp;

    // Constructeurs, getters et setters
}

