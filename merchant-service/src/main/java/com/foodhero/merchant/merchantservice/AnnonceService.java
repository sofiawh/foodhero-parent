package com.foodhero.merchant.merchantservice;

import com.foodhero.merchant.merchantservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public Annonce createAnnonce(Annonce annonce) {
        // Logique pour créer un nouvel utilisateur dans la base de données
//        UserEntity userEntity = new UserEntity();
//        userEntity.setNom(userDTO.getNom());
//        userEntity.setPrenom(userDTO.getPrenom());
//        userEntity.setEmail(userDTO.getEmail());
        // ... Autres attributs

        Annonce savedAnnonce = annonceRepository.save(annonce);

        return savedAnnonce;
    }

    public Annonce getAnnonceById(Long idAnnonce) {
        Annonce annonce = annonceRepository.findById(idAnnonce)
                .orElseThrow(() -> new NotFoundException("Annonce non trouvé avec l'ID : " + idAnnonce));

        return annonce;
    }
    public List<Annonce> getAnnonces(){
        return annonceRepository.findAll();
    }

    // Autres méthodes de service...

//    private UserDTO convertToDTO(UserEntity userEntity) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userEntity.getId());
//        userDTO.setNom(userEntity.getNom());
//        userDTO.setPrenom(userEntity.getPrenom());
//        userDTO.setEmail(userEntity.getEmail());
//        // ... Autres attributs
//
//        return userDTO;
//    }
}

