package com.foodhero.user.userservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/utilisateurs/preferencealim")
public class PreferenceAlimentaireController {

    @Autowired
    private PreferenceAlimentaireService preferenceAlimentaireService;

    @PostMapping
    public ResponseEntity<PreferenceAlimentaireEntity> createPreferenceAlim(@RequestBody PreferenceAlimentaireEntity preferenceAlimentaireEntity) {
        PreferenceAlimentaireEntity createdPreferenceAlimentaire = preferenceAlimentaireService.createPreferenceAlimentaire(preferenceAlimentaireEntity);
        return new ResponseEntity<>(createdPreferenceAlimentaire, HttpStatus.CREATED);
    }

    @GetMapping("/{idPreferenceAlimentaire}")
    public ResponseEntity<PreferenceAlimentaireEntity> getPreferenceAlimentaireById(@PathVariable Long idPreferenceAlimentaire) {
        PreferenceAlimentaireEntity preferenceAlimentaireEntity = preferenceAlimentaireService.getPreferenceAlimentaireById(idPreferenceAlimentaire);
        return new ResponseEntity<>(preferenceAlimentaireEntity, HttpStatus.OK);
    }

    // Autres endpoints du contrôleur...
}

