package com.foodhero.association.associationservice;


import com.foodhero.association.associationservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationService {

    @Autowired
    private AssociationRepository associationRepository;
//
//    @Autowired
//    private UtilisateurRepository utilisateurRepository;

    public AssociationDTO createAssociation(AssociationDTO associationDTO) {
        // Logique pour créer un nouveau commerçant dans la base de données
        AssociationEntity associationEntity = new AssociationEntity();
        associationEntity.setNom(associationDTO.getNom());
        associationEntity.setEmail(associationDTO.getEmail());
        associationEntity.setLocalisation(associationDTO.getLocalisation());

/*
        UserEntity userrEntity = userRepository.findById(commercantDTO.getIdUser())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + commercantDTO.getIdUser()));
        commercantEntity.setUser(userEntity);*/

        AssociationEntity savedAssociation = associationRepository.save(associationEntity);

        return convertToDTO(savedAssociation);
    }
    public List<AssociationDTO> getAssociations(){
        List<AssociationEntity> associations = associationRepository.findAll();
        List<AssociationDTO> associationsDto = new ArrayList<>();
        for(AssociationEntity c:associations){
            associationsDto.add(convertToDTO(c));
        }
        return associationsDto;
    }
    public AssociationDTO getAssociationById(Long idAssociation) {
        AssociationEntity associationEntity = associationRepository.findById(idAssociation)
                .orElseThrow(() -> new NotFoundException("Association non trouvee avec l'ID : " + idAssociation));

        return convertToDTO(associationEntity);
    }

    public AssociationEntity getAssociationWithAnnonces(Long id) {
        return associationRepository.findById(id).orElse(null);
    }

    // Autres méthodes de service...

    private AssociationDTO convertToDTO(AssociationEntity associationEntity) {
        AssociationDTO associationDTO = new AssociationDTO();
        associationDTO.setId(associationEntity.getId());
        associationDTO.setNom(associationEntity.getNom());
        associationDTO.setEmail(associationEntity.getEmail());
        associationDTO.setLocalisation(associationEntity.getLocalisation());
      //  commercantDTO.setIdUser(commercantEntity.getUser().getId());

        return associationDTO;
    }
}

