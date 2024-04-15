package com.foodhero.donation.donationservice;

import com.foodhero.donation.donationservice.client.AnnonceFeignClient;
import com.foodhero.donation.donationservice.client.AssociationFeignClient;
import com.foodhero.donation.donationservice.client.UserFeignClient;
import com.foodhero.donation.donationservice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;

@Service
@RequiredArgsConstructor
public class DonationServiceService {

    private final UserFeignClient userFeignClient;
    private final AssociationFeignClient associationFeignClient;
    private final AnnonceFeignClient annonceFeignClient;
    private final DonationRepository repository;

    public Donation saveDonation(Donation donation){
        return repository.save(donation);
    }

    public List<Donation> findAllDonations(){   return repository.findAll();
    }

    public List</*Long*/String> findDonationUsersByAssociationId(/*Long*/String associationId) {
        List</*Long*/String> donationUserIds = new ArrayList<>();
        List<Donation> enrollments = repository.findAllByAssociationId(associationId);

        for (Donation enrollment : enrollments) {
            donationUserIds.add(enrollment.getUserId());
        }

        return donationUserIds;
    }

    public List</*Long*/String> findDonationAssociationsByUserId(/*Long*/String userId) {
        List</*Long*/String> enrolledCommercantIds = new ArrayList<>();
        List<Donation> enrollments = repository.findAllByUserId(userId);

        for (Donation enrollment : enrollments) {
            enrolledCommercantIds.add(enrollment.getAssociationId());
        }

        return enrolledCommercantIds;
    }

    public Donation findDonationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteDonation(Donation donation) {
        repository.delete(donation);
    }

    public List<Donation> findDonationsByUserId(/*Long*/String userId) {
        // Récupérer les dons par userID en utilisant la méthode du repository
        List<Donation> donations = repository.findAllByUserId(userId);

        // Si aucun don n'est trouvé pour l'utilisateur, vous pouvez lancer une exception ou retourner une liste vide selon votre logique métier
        if (donations == null || donations.isEmpty()) {
            // Lancez une exception appropriée ou retournez une liste vide
            throw new NotFoundException("Aucun don trouvé pour l'utilisateur avec l'ID : " + userId);
        }

        // Retourner la liste des dons récupérés
        return donations;
    }

    public List<Donation> findDonationsByAssociationId(/*Long*/String associationId) {
        // Récupérer les dons par associationID en utilisant la méthode du repository
        List<Donation> donations = repository.findAllByAssociationId(associationId);

        // Si aucun don n'est trouvé pour l'utilisateur, vous pouvez lancer une exception ou retourner une liste vide selon votre logique métier
        if (donations == null || donations.isEmpty()) {
            // Lancez une exception appropriée ou retournez une liste vide
            throw new NotFoundException("Aucun don trouvé pour l'association avec l'ID : " + associationId);
        }

        // Retourner la liste des dons récupérés
        return donations;
    }

    public List<Donation> findDonationsByAnnonceId(Long annonceId) {
        List<Donation> donations = repository.findAllByAnnonceId(annonceId);

        // Si aucun don n'est trouvé pour l'annonce, vous pouvez lancer une exception ou retourner une liste vide selon votre logique métier
        if (donations == null || donations.isEmpty()) {
            // Lancez une exception appropriée ou retournez une liste vide
            throw new NotFoundException("Aucun don trouvé pour l'annonce avec l'ID : " + annonceId);
        }

        // Retourner la liste des dons récupérés
        return donations;
    }

    public List<DonationWithAssociationsAndUsersAndAnnonces> findDonationsWithAssociationsAndUsersAndAnnonces() {
        List<Donation> donations = repository.findAll();
        List<DonationWithAssociationsAndUsersAndAnnonces> donationsWithInfo = new ArrayList<>();

        for (Donation donation : donations) {
          //  ResponseEntity<User> userResponse = userFeignClient.getUserById(donation.getUserId());
            ResponseEntity<Association> associationResponse = associationFeignClient.getAssociationById(donation.getAssociationId());
            ResponseEntity<Annonce> annonceResponse = annonceFeignClient.getAnnonceById(donation.getAnnonceId());

            if (/*userResponse.getStatusCode() == HttpStatus.OK &&*/
                    associationResponse.getStatusCode() == HttpStatus.OK &&
                    annonceResponse.getStatusCode() == HttpStatus.OK) {

//                User user = userResponse.getBody();
                Association association = associationResponse.getBody();
                Annonce annonce = annonceResponse.getBody();

                DonationWithAssociationsAndUsersAndAnnonces donationWithInfo = new DonationWithAssociationsAndUsersAndAnnonces();
                donationWithInfo.setDonation(donation);
                //donationWithInfo.setUser(user);
                donationWithInfo.setAssociation(association);
                donationWithInfo.setAnnonce(annonce);

                donationsWithInfo.add(donationWithInfo);
            }
        }

        return donationsWithInfo;
    }





//    public FullSchoolResponse findSchWithStudents(Integer schoolId) {
//        var school = repository.findById(schoolId)
//                .orElse(
//                        Enrollment.builder()
//                                .name("NOT_FOUND")
//                                .email("NOT_FOUND")
//                                .build()
//                );
//        var students = client.findAllStudentsBySchool(schoolId); // find all the students from the student micro-service
//        return FullSchoolResponse.builder()
//                        .name(school.getName())
//                                .email(school.getEmail())
//                                        .students(students)
//                .build();
//    }
}
