package com.foodhero.association.associationservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.foodhero.association.associationservice.client.DonationFeignClient;
import com.foodhero.association.associationservice.client.UserFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//
@SpringBootTest
class AssociationControllerTest {

    @InjectMocks
    private AssociationController associationController;

    @Mock
    private AssociationService associationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAssociation() {
        // Arrange
        AssociationDTO associationDTO = new AssociationDTO();
        associationDTO.setNom("Association Test");
        associationDTO.setEmail("test@example.com");
        associationDTO.setLocalisation("Test Location");

        AssociationDTO createdAssociationDTO = new AssociationDTO();
        createdAssociationDTO.setId("1");
        createdAssociationDTO.setNom(associationDTO.getNom());
        createdAssociationDTO.setEmail(associationDTO.getEmail());
        createdAssociationDTO.setLocalisation(associationDTO.getLocalisation());

        when(associationService.createAssociation(any(AssociationDTO.class))).thenReturn(createdAssociationDTO);

        // Act
        ResponseEntity<AssociationDTO> response = associationController.createAssociation(associationDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(createdAssociationDTO.getId(), response.getBody().getId());
        assertEquals(createdAssociationDTO.getNom(), response.getBody().getNom());
        assertEquals(createdAssociationDTO.getEmail(), response.getBody().getEmail());
        assertEquals(createdAssociationDTO.getLocalisation(), response.getBody().getLocalisation());
    }


}

