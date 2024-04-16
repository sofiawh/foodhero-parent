package com.foodhero.merchant.merchantservice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnnonceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnnonceService annonceService;

    @InjectMocks
    private AnnonceController annonceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(annonceController).build();
    }

    @Test
    public void testCreateAnnonce() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file", "th.jpeg", MediaType.IMAGE_JPEG_VALUE, getClass().getResourceAsStream("/static/th.jpeg"));

//        byte[] content = Files.readAllBytes(Paths.get(getClass().getResource("/th.jpg").toURI()));
//        MockMultipartFile file = new MockMultipartFile("file", "th.jpg", MediaType.IMAGE_JPEG_VALUE, content);

//        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", MediaType.IMAGE_JPEG_VALUE, Files.readAllBytes(Paths.get(/*System.getProperty("user.home") + "/food/annonces/" + "th.jpeg"*//*"path/to/test.jpg"*/"C:/Users/HP/food/annonces/th.jpeg")));
        Annonce annonce = new Annonce(); // Créez un objet Annonce à tester

        // Définir le comportement attendu lors de l'appel au service
        when(annonceService.createAnnonce(any(Annonce.class))).thenReturn(annonce);

        // Effectuez la requête POST pour créer une annonce avec un fichier joint
        this.mockMvc.perform(multipart("/api/v1/commercants/annonce")
                        .file(file)
                        .param("annonce", "{\"id\":1,\"titre\":\"Test\",\"description\":\"Test description\",\"prix\":100,\"ratings\":4,\"categorie\":\"Test category\",\"image\":\"test.jpg\",\"revenue\":0,\"ordersDonation\":0,\"message\":\"\"}")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isCreated());

        // Vérifiez si la méthode du service a été appelée une fois
        verify(annonceService, times(1)).createAnnonce(any(Annonce.class));
    }

    // Ajoutez d'autres tests pour les autres méthodes du contrôleur
}

