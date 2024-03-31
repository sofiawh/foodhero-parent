package com.foodhero.association.associationservice;


import com.foodhero.association.associationservice.client.DonationFeignClient;
import com.foodhero.association.associationservice.client.UserFeignClient;
import com.foodhero.association.associationservice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/associations")
@RequiredArgsConstructor
public class AssociationController {

    @Autowired
    private AssociationService associationService;
    private final DonationFeignClient donationFeignClient;
    private final UserFeignClient userFeignClient;


    @PostMapping
    public ResponseEntity<AssociationDTO> createAssociation(@RequestBody AssociationDTO associationDTO) {
        AssociationDTO createdAssociation = associationService.createAssociation(associationDTO);
        return new ResponseEntity<>(createdAssociation, HttpStatus.CREATED);
    }

    @GetMapping("/{idAssociation}")
    public ResponseEntity<AssociationDTO> getAssociationById(@PathVariable Long idAssociation) {
        AssociationDTO association = associationService.getAssociationById(idAssociation);
        return new ResponseEntity<>(association, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AssociationDTO>> getAssociations() {

        return new ResponseEntity<>(associationService.getAssociations(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<AssociationEntity> getAssociationWithAnnonces(@PathVariable Long id) {
//        AssociationEntity commercant = commercantService.getCommercantWithAnnonces(id);
//        if (commercant != null) {
//            return new ResponseEntity<>(commercant, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/{associationId}/users")
    public ResponseEntity<List<User>> getAssociationUsers(@PathVariable Long associationId) {
        ResponseEntity<List<Long>> donatedUsersResponse = donationFeignClient.getDonateddUsersByAssociationId(associationId);

        if (donatedUsersResponse.getStatusCode() != HttpStatus.OK || donatedUsersResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Long> userIds = donatedUsersResponse.getBody();
        // Liste pour stocker les détails des users
        List<User> users = new ArrayList<>();

        for (Long userId : userIds) {
            ResponseEntity<User> userResponse = userFeignClient.getUserById(userId);

            if (userResponse.getStatusCode() == HttpStatus.OK && userResponse.getBody() != null) {
                users.add(userResponse.getBody());
            }
        }

        return ResponseEntity.ok(users);


    }

    @GetMapping("/{associationId}/dons")
    public ResponseEntity<List<Donation>> getDonsByAssociationId(@PathVariable Long associationId) {
        // Appeler le service de dons pour récupérer les dons par associationID
        List<Donation> donations = donationFeignClient.getDonsByAssociationId(associationId)
                .orElseThrow(() -> new NotFoundException("Erreur lors de la récupération des dons pour l'association avec l'ID : " + associationId));

        return ResponseEntity.ok(donations);
    }


//    @GetMapping("/{commercantId}/users")
//    public ResponseEntity<List<User>> getCommercantUsers(@PathVariable Long commercantId) {
//        ResponseEntity<List<Long>> enrolledUsersResponse = enrollmentFeignClient.getEnrolledUsersByCommercantId(commercantId);
//
//        if (enrolledUsersResponse.getStatusCode() != HttpStatus.OK || enrolledUsersResponse.getBody() == null) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        List<Long> userIds = enrolledUsersResponse.getBody();
//        // Liste pour stocker les détails des users
//        List<User> users = new ArrayList<>();
//
//        for (Long userId : userIds) {
//            ResponseEntity<User> userResponse = userFeignClient.getUserById(userId);
//
//            if (userResponse.getStatusCode() == HttpStatus.OK && userResponse.getBody() != null) {
//                users.add(userResponse.getBody());
//            }
//        }
//
//        return ResponseEntity.ok(users);
//
//
//    }


    // Autres endpoints du contrôleur...
}

