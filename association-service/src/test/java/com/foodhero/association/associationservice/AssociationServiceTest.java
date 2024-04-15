package com.foodhero.association.associationservice;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssociationServiceTest {

    @Mock
    private AssociationRepository associationRepository;

    @InjectMocks
    private AssociationService associationService;

    @Test
    void testCreateAssociation() {
        // Arrange
        AssociationDTO associationDTO = new AssociationDTO();
        associationDTO.setNom("Association Test");
        associationDTO.setEmail("test@example.com");
        associationDTO.setLocalisation("Test Location");

        AssociationEntity createdEntity = new AssociationEntity();
        createdEntity.setId("1L");
        createdEntity.setNom(associationDTO.getNom());
        createdEntity.setEmail(associationDTO.getEmail());
        createdEntity.setLocalisation(associationDTO.getLocalisation());

        when(associationRepository.save(any(AssociationEntity.class))).thenReturn(createdEntity);

        // Act
        AssociationDTO createdDTO = associationService.createAssociation(associationDTO);

        // Assert
        assertNotNull(createdDTO);
        assertEquals(associationDTO.getNom(), createdDTO.getNom());
        assertEquals(associationDTO.getEmail(), createdDTO.getEmail());
        assertEquals(associationDTO.getLocalisation(), createdDTO.getLocalisation());
    }


}

