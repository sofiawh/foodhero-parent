package com.foodhero.user.userservice;


import com.foodhero.user.userservice.client.AssociationFeignClient;
import com.foodhero.user.userservice.client.CommercantFeignClient;
import com.foodhero.user.userservice.client.DonationFeignClient;
import com.foodhero.user.userservice.client.EnrollmentFeignClient;
import com.foodhero.user.userservice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EnrollmentFeignClient enrollmentFeignClient;
    @Autowired
    private DonationFeignClient donationFeignClient;
    @Autowired
    private CommercantFeignClient commercantFeignClient;
    @Autowired
    private AssociationFeignClient associationFeignClient;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

//    @GetMapping("/{idUser}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long idUser) {
//        UserDTO user = userService.getUserById(idUser);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
    @GetMapping("/{idUser}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long idUser) {
        UserEntity user = userService.getUserById(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/keycloak/{idKeycloak}")
    public ResponseEntity<UserEntity> getUserByIdKeycloak(@PathVariable Long idKeycloak) {
        UserEntity user = userService.getUserByIdKeycloak(idKeycloak);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/{userId}/commercants")
    public ResponseEntity<List<Commercant>> getUserCommercants(@PathVariable Long userId) {
        ResponseEntity<List<Long>> enrolledCommercantsResponse = enrollmentFeignClient.getEnrolledCommercantsByUserId(userId);

        if (enrolledCommercantsResponse.getStatusCode() != HttpStatus.OK || enrolledCommercantsResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Long> commercantIds = enrolledCommercantsResponse.getBody();
        // Liste pour stocker les détails des commercants
        List<Commercant> commercants = new ArrayList<>();

        for (Long commercantId : commercantIds) {
            ResponseEntity<Commercant> commercantResponse = commercantFeignClient.getCommercantWithAnnonces(commercantId);

            if (commercantResponse.getStatusCode() == HttpStatus.OK && commercantResponse.getBody() != null) {
                commercants.add(commercantResponse.getBody());
            }
        }

        return ResponseEntity.ok(commercants);


    }

    ////////   with  association
    @GetMapping("/{userId}/associations")
    public ResponseEntity<List<Association>> getUserAssociations(@PathVariable Long userId) {
        ResponseEntity<List<Long>> donatedAssociationsResponse = donationFeignClient.getDonatedAssociationsByUserId(userId);

        if (donatedAssociationsResponse.getStatusCode() != HttpStatus.OK || donatedAssociationsResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Long> associationIds = donatedAssociationsResponse.getBody();
        // Liste pour stocker les détails des associations
        List<Association> associations = new ArrayList<>();

        for (Long associationId : associationIds) {
            ResponseEntity<Association> associationResponse = associationFeignClient.getAssociationById(associationId);

            if (associationResponse.getStatusCode() == HttpStatus.OK && associationResponse.getBody() != null) {
                associations.add(associationResponse.getBody());
            }
        }

        return ResponseEntity.ok(associations);


    }
    @GetMapping("/{userId}/dons")
    public ResponseEntity<List<Donation>> getDonsByUserId(@PathVariable Long userId) {
        // Appeler le service de dons pour récupérer les dons par userID
        List<Donation> donations = donationFeignClient.getDonsByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Erreur lors de la récupération des dons pour l'utilisateur avec l'ID : " + userId));

        return ResponseEntity.ok(donations);
    }
    ////


    @PutMapping("/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long idUser, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(idUser, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.noContent().build();
    }


}

