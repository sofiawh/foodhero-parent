package com.foodhero.merchant.merchantservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MerchantServiceApplicationTests {

    @Test
    void contextLoads() {
    }

//
//    @RunWith(MockitoJUnitRunner.class)
//    public class MerchantServiceApplicationTests /*CommercantServiceTest*/ {
//
//        @Mock
//        private CommercantRepository commercantRepository;
//
//        @Mock
//        private UtilisateurRepository utilisateurRepository;
//
//        @InjectMocks
//        private CommercantService commercantService;
//
//        @Test
//        public void testCreateCommercant() {
//            // Mise en place du test
//
//            UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
//            utilisateurEntity.setId(1L);
//
//            when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateurEntity));
//
//            CommercantEntity commercantEntity = new CommercantEntity();
//            commercantEntity.setId(1L);
//            commercantEntity.setNomCommerce("Nom du commerce");
//            commercantEntity.setDescription("Description du commerce");
//            commercantEntity.setUtilisateur(utilisateurEntity);
//
//            when(commercantRepository.save(any(CommercantEntity.class))).thenReturn(commercantEntity);
//
//            // Exécution du service
//            CommercantDTO commercantDTO = new CommercantDTO();
//            commercantDTO.setNomCommerce("Nom du commerce");
//            commercantDTO.setDescription("Description du commerce");
//            commercantDTO.setIdUtilisateur(1L);
//
//            CommercantDTO createdCommercant = commercantService.createCommercant(commercantDTO);
//
//            // Vérification des résultats
//            assertNotNull(createdCommercant);
//            assertEquals("Nom du commerce", createdCommercant.getNomCommerce());
//            assertEquals("Description du commerce", createdCommercant.getDescription());
//            assertEquals(1L, createdCommercant.getIdUtilisateur());
//        }
//
//        // Autres tests...
//    }

}
