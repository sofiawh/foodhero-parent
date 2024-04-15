package com.foodhero.user.userservice;

import com.foodhero.user.userservice.client.AssociationFeignClient;
import com.foodhero.user.userservice.client.DonationFeignClient;
import com.foodhero.user.userservice.client.EnrollmentFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private EnrollmentFeignClient enrollmentFeignClient;

    @Mock
    private CommercantFeignClient commercantFeignClient;

    @Mock
    private DonationFeignClient donationFeignClient;

    @Mock
    private AssociationFeignClient associationFeignClient;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser() {
        // Créer un objet UserDTO de test
        UserDTO userDTO = new UserDTO();
        userDTO.setNom("John");
        userDTO.setPrenom("Doe");
        userDTO.setEmail("john.doe@example.com");

        // Créer un objet UserDTO simulé retourné par le service
        UserDTO createdUserDTO = new UserDTO();
        createdUserDTO.setId(1L);
        createdUserDTO.setNom(userDTO.getNom());
        createdUserDTO.setPrenom(userDTO.getPrenom());
        createdUserDTO.setEmail(userDTO.getEmail());

        // Définir le comportement simulé du service lors de l'appel de la méthode createUser()
        when(userService.createUser(userDTO)).thenReturn(createdUserDTO);

        // Appeler la méthode du contrôleur
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        // Vérifier si la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdUserDTO.getId(), response.getBody().getId());
        assertEquals(createdUserDTO.getNom(), response.getBody().getNom());
        assertEquals(createdUserDTO.getPrenom(), response.getBody().getPrenom());
        assertEquals(createdUserDTO.getEmail(), response.getBody().getEmail());

        // Vérifier si la méthode du service a été appelée une fois avec le bon argument
        verify(userService, times(1)).createUser(userDTO);
    }


}
