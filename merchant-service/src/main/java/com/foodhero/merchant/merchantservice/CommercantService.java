package com.foodhero.merchant.merchantservice;


import com.foodhero.merchant.merchantservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommercantService {

    @Autowired
    private CommercantRepository commercantRepository;
//
//    @Autowired
//    private UtilisateurRepository utilisateurRepository;

    public CommercantDTO createCommercant(CommercantDTO commercantDTO) {
        // Logique pour créer un nouveau commerçant dans la base de données
        CommercantEntity commercantEntity = new CommercantEntity();
        commercantEntity.setNomCommerce(commercantDTO.getNomCommerce());
        commercantEntity.setDescription(commercantDTO.getDescription());
        commercantEntity.setAdresse(commercantDTO.getAdresse());

/*
        UserEntity userrEntity = userRepository.findById(commercantDTO.getIdUser())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + commercantDTO.getIdUser()));
        commercantEntity.setUser(userEntity);*/

        CommercantEntity savedCommercant = commercantRepository.save(commercantEntity);

        return convertToDTO(savedCommercant);
    }
    public List<CommercantDTO> getCommercants(){
        List<CommercantEntity> commercants = commercantRepository.findAll();
        List<CommercantDTO> commercantsDto = new ArrayList<>();
        for(CommercantEntity c:commercants){
            commercantsDto.add(convertToDTO(c));
        }
        return commercantsDto;
    }
    public CommercantDTO getCommercantById(Long idCommercant) {
        CommercantEntity commercantEntity = commercantRepository.findById(idCommercant)
                .orElseThrow(() -> new NotFoundException("Commerçant non trouvé avec l'ID : " + idCommercant));

        return convertToDTO(commercantEntity);
    }

    public CommercantEntity getCommercantWithAnnonces(Long id) {
        return commercantRepository.findById(id).orElse(null);
    }


    public CommercantDTO updateCommercant(Long idCommercant, CommercantDTO commercantDTO) {
        Optional<CommercantEntity> optionalCommercant = commercantRepository.findById(idCommercant);
        if (optionalCommercant.isPresent()) {
            CommercantEntity existingCommercant = optionalCommercant.get();
            existingCommercant.setNomCommerce(commercantDTO.getNomCommerce());
            existingCommercant.setDescription(commercantDTO.getDescription());
            existingCommercant.setAdresse(commercantDTO.getAdresse());

            CommercantEntity updatedCommercant = commercantRepository.save(existingCommercant);
            return convertToDTO(updatedCommercant);
        } else {
            throw new NotFoundException("Commerçant non trouvé avec l'ID : " + idCommercant);
        }
    }

    public void deleteCommercant(Long idCommercant) {
        if (commercantRepository.existsById(idCommercant)) {
            commercantRepository.deleteById(idCommercant);
        } else {
            throw new NotFoundException("Commerçant non trouvé avec l'ID : " + idCommercant);
        }
    }

    private CommercantDTO convertToDTO(CommercantEntity commercantEntity) {
        CommercantDTO commercantDTO = new CommercantDTO();
        commercantDTO.setId(commercantEntity.getId());
        commercantDTO.setNomCommerce(commercantEntity.getNomCommerce());
        commercantDTO.setDescription(commercantEntity.getDescription());
        commercantDTO.setAdresse(commercantEntity.getAdresse());
      //  commercantDTO.setIdUser(commercantEntity.getUser().getId());

        return commercantDTO;
    }
}

