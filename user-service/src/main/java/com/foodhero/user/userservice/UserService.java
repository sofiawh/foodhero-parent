package com.foodhero.user.userservice;

import com.foodhero.user.userservice.exceptions.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
       
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
                .orElseThrow(() -> new NotFoundException("User non trouvé avec l'ID : " + idUser));
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
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long idUser, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("User non trouvé avec l'ID : " + idUser));
        userEntity.setNom(userDTO.getNom());
        userEntity.setPrenom(userDTO.getPrenom());
        userEntity.setEmail(userDTO.getEmail());
        UserEntity updatedUser = userRepository.save(userEntity);
        return convertToDTO(updatedUser);
    }

    public void deleteUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

    private UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNom(userDTO.getNom());
        userEntity.setPrenom(userDTO.getPrenom());
        userEntity.setEmail(userDTO.getEmail());
        
        return userEntity;
    }

//    private UserDTO convertToDTO(UserEntity userEntity) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(userEntity.getId());
//        userDTO.setNom(userEntity.getNom());
//        userDTO.setPrenom(userEntity.getPrenom());
//        userDTO.setEmail(userEntity.getEmail());
//        // Définir d'autres attributs si nécessaire
//        return userDTO;
//    }
}

