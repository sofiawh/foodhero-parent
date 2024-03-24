package com.foodhero.user.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

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

    // Autres endpoints du contrôleur...
}

