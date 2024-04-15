package com.foodhero.merchant.merchantservice;


import com.foodhero.merchant.merchantservice.client.DonationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/commercants/annonce")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private DonationFeignClient donationFeignClient;

//    @PostMapping
//    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) {
//        Annonce createdAnnonce = annonceService.createAnnonce(annonce);
//        return new ResponseEntity<>(createdAnnonce, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<Annonce> createAnnonce(@RequestParam("file") MultipartFile file, /*@RequestBody*/ @ModelAttribute Annonce annonce) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(System.getProperty("user.home") + "/food/annonces/" + fileName);
            Files.write(path, file.getBytes());
            annonce.setImage(fileName);
            Annonce createdAnnonce = annonceService.createAnnonce(annonce);
            // Logique pour créer une annonce dans la base de données
            // Annonce createdAnnonce = annonceService.createAnnonce(annonce);
            // Retournez la réponse avec la nouvelle annonce créée
            return new ResponseEntity<>(createdAnnonce, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idAnnonce}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long idAnnonce) {
        Annonce annonce = annonceService.getAnnonceById(idAnnonce);
        return new ResponseEntity<>(annonce, HttpStatus.OK);
    }

    @GetMapping(path="/imageAnnonce/{idAnnonce}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long idAnnonce) throws Exception {
        Annonce a = annonceService.getAnnonceById(idAnnonce);
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/food/annonces/" + a.getImage()));

    }

    @GetMapping
    public ResponseEntity<List<Annonce>> getAnnonces(){
        List<Annonce> annonces = annonceService.getAnnonces();
        for (Annonce annonce : annonces) {
            String imageUrl = getImageUrl(annonce.getId());
            annonce.setImage(imageUrl);
        }
        return new ResponseEntity<>(annonces, HttpStatus.OK);
        // return new ResponseEntity<>(annonceService.getAnnonces(),HttpStatus.OK);
    }

    private String getImageUrl(Long idAnnonce) {
        return "/api/v1/commercants/annonce/imageAnnonce/" + idAnnonce;
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

//    @PostMapping("/{id}/commentaires")
//    public ResponseEntity<Comme> addProductComment(@PathVariable Long id, @RequestBody Commentaire newCommentObj) {
//        // Assurez-vous que le commentaire contient l'ID du produit
//        newCommentObj.setProductId(id);
//        // Ajoutez le commentaire
//        Commentaire createdComment = productService.addProductComment(newCommentObj);
//        // Retournez le commentaire créé avec un statut HTTP approprié
//        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
//    }
}



