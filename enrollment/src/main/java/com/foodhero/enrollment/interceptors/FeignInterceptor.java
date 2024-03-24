package com.foodhero.enrollment.interceptors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // Récupérer le contexte de sécurité
        SecurityContext context = SecurityContextHolder.getContext();

        // Récupérer l'authentification à partir du contexte de sécurité
        Authentication authentication = context.getAuthentication();
        System.out.println("***********************************************");
        System.out.println(authentication.getClass().getName());

        // Vérifier si l'authentification est une instance de JwtAuthenticationToken
        if (authentication instanceof JwtAuthenticationToken) {
            // Si oui, extraire le jeton JWT
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            String accessToken = jwtToken.getToken().getTokenValue();
            System.out.println("******* "+accessToken+"*******");
            // Ajouter le jeton JWT aux en-têtes de la requête Feign
            template.header("Authorization", "Bearer " + accessToken);
        }
    }
}

