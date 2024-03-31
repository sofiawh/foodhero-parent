package com.foodhero.donation.donationservice;


import com.foodhero.donation.donationservice.client.AnnonceFeignClient;
import com.foodhero.donation.donationservice.client.AssociationFeignClient;
import com.foodhero.donation.donationservice.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/donations")
@RequiredArgsConstructor
public class DonationController {
    @Autowired
    private DonationServiceService service;

    @Autowired
    private AssociationFeignClient associationFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private AnnonceFeignClient annonceFeignClient;


    @PostMapping
    public ResponseEntity<Donation> donationUser(@RequestBody Donation donation) {
        // Validate user and association existence
        if (isValidUser(donation.getUserId()) && isValidAssociation(donation.getAssociationId())) {
            return ResponseEntity.ok(service.saveDonation(donation));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/associations/{associationId}/users")
    public ResponseEntity<List<Long>> getDonationUsersByAssociationId(@PathVariable Long associationId) {
        List<Long> donationUserIds = service.findDonationUsersByAssociationId(associationId);
        return ResponseEntity.ok(donationUserIds);
    }

    @GetMapping("/users/{userId}/dons")
    public ResponseEntity<List<Donation>> getDonsByUserId(@PathVariable Long userId) {
        List<Donation> donations = service.findDonationsByUserId(userId);
        return ResponseEntity.ok(donations);
    }
    @GetMapping("/annonces/{annonceId}/dons")
    public ResponseEntity<List<Donation>> getDonsByAnnonceId(@PathVariable Long annonceId) {
        List<Donation> donations = service.findDonationsByAnnonceId(annonceId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/associations/{associationId}/dons")
    public ResponseEntity<List<Donation>> getDonsByAssociationId(@PathVariable Long associationId) {
        List<Donation> donations = service.findDonationsByAssociationId(associationId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/users/{userId}/associations")
    public ResponseEntity<List<Long>> getdonationAssociationsByUserId(@PathVariable Long userId) {
        List<Long> donationAssociationIds = service.findDonationAssociationsByUserId(userId);
        return ResponseEntity.ok(donationAssociationIds);
    }
    @GetMapping
    public ResponseEntity<List<Donation>> getDonations() {
        List<Donation> donations = service.findAllDonations();
        return ResponseEntity.ok(donations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id, @RequestBody Donation donation) {
        // Vérifie si la donation avec l'ID donné existe
        Donation existingDonation = service.findDonationById(id);
        if (existingDonation == null) {
            return ResponseEntity.notFound().build();
        }

        // Met à jour les champs de la donation existante avec les nouvelles valeurs
        existingDonation.setUserId(donation.getUserId());
        existingDonation.setAssociationId(donation.getAssociationId());
        existingDonation.setMontant(donation.getMontant());

        // Enregistre les modifications dans la base de données
        Donation updatedDonation = service.saveDonation(existingDonation);
        return ResponseEntity.ok(updatedDonation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        // Vérifie si la donation avec l'ID donné existe
        Donation existingDonation = service.findDonationById(id);
        if (existingDonation == null) {
            return ResponseEntity.notFound().build();
        }

        // Supprime la donation de la base de données
        service.deleteDonation(existingDonation);
        return ResponseEntity.noContent().build();
    }



    private boolean isValidUser(Long userId) {
        ResponseEntity<User> response = userFeignClient.getUserById(userId);
        return response.getStatusCode() == HttpStatus.OK;
    }

    private boolean isValidAssociation(Long associationId) {
        ResponseEntity<Association> response = associationFeignClient.getAssociationById(associationId);
        return response.getStatusCode() == HttpStatus.OK;
    }

    // other methods
}


//public class EnrollmentController {
//
//    private final EnrollmentService service;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void save(
//            @RequestBody Enrollment enrollment
//    ){
//        service.saveSchool(enrollment);
//    }
//    @GetMapping
//    public ResponseEntity<List<Enrollment>> findAllEnrollments(){
//        return ResponseEntity.ok(service.findAllEnrollments());
//
//    }
//    @GetMapping("/with-students/{school-id}")
//    public ResponseEntity<FullSchoolResponse> findAllSchools(
//            @PathVariable("school-id") Integer schoolId
//    ){
//        return ResponseEntity.ok(service.findSchWithStudents(schoolId));
//    }
//}
