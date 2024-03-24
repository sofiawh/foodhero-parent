package com.foodhero.user.userservice;

import com.foodhero.user.userservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        // Logique pour créer un nouvel utilisateur dans la base de données
        UserEntity userEntity = new UserEntity();
        userEntity.setNom(userDTO.getNom());
        userEntity.setPrenom(userDTO.getPrenom());
        userEntity.setEmail(userDTO.getEmail());
        // ... Autres attributs

        UserEntity savedUser = userRepository.save(userEntity);

        return convertToDTO(savedUser);
    }

    public UserEntity getUserById(Long idUser) {
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + idUser));
        return userEntity;
        //return convertToDTO(userEntity);
    }

    // Autres méthodes de service...

    private UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setNom(userEntity.getNom());
        userDTO.setPrenom(userEntity.getPrenom());
        userDTO.setEmail(userEntity.getEmail());
        // ... Autres attributs

        return userDTO;
    }
}

