package com.foodhero.user.userservice;


import com.foodhero.user.userservice.client.CommercantFeignClient;
import com.foodhero.user.userservice.client.EnrollmentFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private CommercantFeignClient commercantFeignClient;

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
    @GetMapping
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
        // Liste pour stocker les détails des étudiants
        List<Commercant> commercants = new ArrayList<>();

        for (Long commercantId : commercantIds) {
            ResponseEntity<Commercant> commercantResponse = commercantFeignClient.getCommercantWithAnnonces(commercantId);

            if (commercantResponse.getStatusCode() == HttpStatus.OK && commercantResponse.getBody() != null) {
                commercants.add(commercantResponse.getBody());
            }
        }

        return ResponseEntity.ok(commercants);


    }
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

