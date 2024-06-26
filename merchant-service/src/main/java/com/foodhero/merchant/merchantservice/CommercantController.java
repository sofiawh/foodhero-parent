package com.foodhero.merchant.merchantservice;


import com.foodhero.merchant.merchantservice.client.EnrollmentFeignClient;
import com.foodhero.merchant.merchantservice.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/commercants")
@RequiredArgsConstructor
public class CommercantController {

    @Autowired
    private CommercantService commercantService;
    private final EnrollmentFeignClient enrollmentFeignClient;
    private final UserFeignClient userFeignClient;


    @PostMapping
    public ResponseEntity<CommercantDTO> createCommercant(@RequestBody CommercantDTO commercantDTO) {
       // commercantDTO.setId("14252");
        CommercantDTO createdCommercant = commercantService.createCommercant(commercantDTO);
        return new ResponseEntity<>(createdCommercant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommercantDTO>> getCommercants() {

        return new ResponseEntity<>(commercantService.getCommercants(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommercantEntity> getCommercantWithAnnonces(@PathVariable Long id) {
        CommercantEntity commercant = commercantService.getCommercantWithAnnonces(id);
        if (commercant != null) {
            return new ResponseEntity<>(commercant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/keycloak/{idKeyCloak}")
    public ResponseEntity<CommercantDTO> getCommercantByIdKeyCloak(@PathVariable Long idKeyCloak) {
        CommercantDTO commercant = commercantService.getCommercantByIdKeyCloak(idKeyCloak);
        if (commercant != null) {
            return new ResponseEntity<>(commercant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{commercantId}/users")
    public ResponseEntity<List<User>> getCommercantUsers(@PathVariable Long commercantId) {
        ResponseEntity<List<Long>> enrolledUsersResponse = enrollmentFeignClient.getEnrolledUsersByCommercantId(commercantId);

        if (enrolledUsersResponse.getStatusCode() != HttpStatus.OK || enrolledUsersResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<Long> userIds = enrolledUsersResponse.getBody();
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

    @PutMapping("/{id}")
    public ResponseEntity<CommercantDTO> updateCommercant(@PathVariable Long id, @RequestBody CommercantDTO commercantDTO) {
        CommercantDTO updatedCommercant = commercantService.updateCommercant(id, commercantDTO);
        return ResponseEntity.ok(updatedCommercant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommercant(@PathVariable Long id) {
        commercantService.deleteCommercant(id);
        return ResponseEntity.noContent().build();
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

