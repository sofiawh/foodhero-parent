package com.foodhero.merchant.merchantservice;


import com.foodhero.merchant.merchantservice.client.DonationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/commercants/annonce")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private DonationFeignClient donationFeignClient;

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
        Annonce createdAnnonce = annonceService.createAnnonce(annonce);
        return new ResponseEntity<>(createdAnnonce, HttpStatus.CREATED);
    }

    @GetMapping("/{idAnnonce}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long idAnnonce) {
        Annonce annonce = annonceService.getAnnonceById(idAnnonce);
        return new ResponseEntity<>(annonce, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Annonce>> getAnnonces(){
        return new ResponseEntity<>(annonceService.getAnnonces(),HttpStatus.OK);
    }


    @PutMapping("/{idAnnonce}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable Long idAnnonce, @RequestBody Annonce updatedAnnonce) {
        Annonce annonce = annonceService.updateAnnonce(idAnnonce, updatedAnnonce);
        return new ResponseEntity<>(annonce, HttpStatus.OK);
    }

    @DeleteMapping("/{idAnnonce}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long idAnnonce) {
        annonceService.deleteAnnonce(idAnnonce);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idAnnonce}/dons")
    public ResponseEntity<List<Donation>> getDonsByAnnonceId(@PathVariable Long idAnnonce) {
        Optional<List<Donation>> donations = donationFeignClient.getDonsByAnnonceId(idAnnonce);

        if (donations.isPresent()) {
            return new ResponseEntity<>(donations.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



