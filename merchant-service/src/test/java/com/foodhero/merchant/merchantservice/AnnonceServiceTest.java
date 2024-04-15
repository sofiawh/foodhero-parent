package com.foodhero.merchant.merchantservice;

import com.foodhero.merchant.merchantservice.Annonce;
import com.foodhero.merchant.merchantservice.AnnonceRepository;
import com.foodhero.merchant.merchantservice.AnnonceService;
import com.foodhero.merchant.merchantservice.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnnonceServiceTest {

    @Mock
    private AnnonceRepository annonceRepository;

    @InjectMocks
    private AnnonceService annonceService;

    @Test
    public void testCreateAnnonce() {
        Annonce annonceToSave = new Annonce();
        annonceToSave.setTitre("TestAnnonce");
        annonceToSave.setDescription("TestDescription");
        when(annonceRepository.save(annonceToSave)).thenReturn(annonceToSave);

        Annonce savedAnnonce = annonceService.createAnnonce(annonceToSave);

        assertEquals(annonceToSave, savedAnnonce);
        verify(annonceRepository, times(1)).save(annonceToSave);
    }

    @Test
    public void testGetAnnonceById() {
        Long idAnnonce = 1L;
        Annonce expectedAnnonce = new Annonce();
        when(annonceRepository.findById(idAnnonce)).thenReturn(Optional.of(expectedAnnonce));

        Annonce retrievedAnnonce = annonceService.getAnnonceById(idAnnonce);

        assertEquals(expectedAnnonce, retrievedAnnonce);
    }

    @Test
    public void testGetAnnonceById_NotFound() {
        Long idAnnonce = 1L;
        when(annonceRepository.findById(idAnnonce)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> annonceService.getAnnonceById(idAnnonce));
    }

    // Ajoutez d'autres tests pour les autres méthodes du service d'annonce
}

