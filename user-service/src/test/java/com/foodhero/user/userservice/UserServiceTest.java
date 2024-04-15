package com.foodhero.user.userservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        // Créer un objet UserDTO fictif
        UserDTO userDTO = new UserDTO();
        userDTO.setNom("John");
        userDTO.setPrenom("Doe");
        userDTO.setEmail("john.doe@example.com");

        // Créer un objet UserEntity fictif à retourner lors de l'appel à save()
        UserEntity savedUserEntity = new UserEntity();
        savedUserEntity.setId(1L);
        savedUserEntity.setName(userDTO.getNom());
        savedUserEntity.setPrenom(userDTO.getPrenom());
        savedUserEntity.setEmail(userDTO.getEmail());

        // Définir le comportement de userRepository.save() pour retourner savedUserEntity
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        // Appeler la méthode createUser() du service
        UserDTO createdUserDTO = userService.createUser(userDTO);

        // Vérifier que l'utilisateur retourné par le service correspond à celui attendu
        assertNotNull(createdUserDTO);
        assertEquals(savedUserEntity.getId(), createdUserDTO.getId());
        assertEquals(savedUserEntity.getName(), createdUserDTO.getNom());
        assertEquals(savedUserEntity.getPrenom(), createdUserDTO.getPrenom());
        assertEquals(savedUserEntity.getEmail(), createdUserDTO.getEmail());

        // Vérifier que la méthode save() de userRepository a été appelée une fois
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    // Ajoutez d'autres tests pour les autres méthodes du service UserService
}

//@RunWith(MockitoJUnitRunner.class)

/*
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    public void testCreateUser() {
        // Mise en place du test

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1L);
        utilisateurEntity.setNom("Nom");
        utilisateurEntity.setPrenom("Prenom");
        utilisateurEntity.setEmail("email@example.com");

        when(utilisateurRepository.save(any(UtilisateurEntity.class))).thenReturn(utilisateurEntity);

        // Exécution du service
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNom("Nom");
        utilisateurDTO.setPrenom("Prenom");
        utilisateurDTO.setEmail("email@example.com");

        UtilisateurDTO createdUser = utilisateurService.createUser(utilisateurDTO);

        // Vérification des résultats
        assertNotNull(createdUser);
        assertEquals("Nom", createdUser.getNom());
        assertEquals("Prenom", createdUser.getPrenom());
        assertEquals("email@example.com", createdUser.getEmail());
    }

    // Autres tests...*/


