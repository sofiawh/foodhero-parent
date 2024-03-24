package com.foodhero.user.userservice;

import com.foodhero.user.userservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceAlimentaireService {

    @Autowired
    private PreferenceAlimentaireRepository preferenceAlimentaireRepository;

    public PreferenceAlimentaireEntity createPreferenceAlimentaire(PreferenceAlimentaireEntity preferenceAlimentaireEntity) {
        // Logique pour créer un nouvel utilisateur dans la base de données
//        UserEntity userEntity = new UserEntity();
//        userEntity.setNom(userDTO.getNom());
//        userEntity.setPrenom(userDTO.getPrenom());
//        userEntity.setEmail(userDTO.getEmail());
        // ... Autres attributs

        PreferenceAlimentaireEntity savedPreferenceAlimentaire = preferenceAlimentaireRepository.save(preferenceAlimentaireEntity);

        return savedPreferenceAlimentaire;
    }

    public PreferenceAlimentaireEntity getPreferenceAlimentaireById(Long idPreferenceAlimentaire) {
        PreferenceAlimentaireEntity preferenceAlimentaireEntity = preferenceAlimentaireRepository.findById(idPreferenceAlimentaire)
                .orElseThrow(() -> new NotFoundException("PreferenceAlimentaire non trouvé avec l'ID : " + idPreferenceAlimentaire));

        return preferenceAlimentaireEntity;
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

