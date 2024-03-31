package com.foodhero.donation.donationservice;

import com.foodhero.donation.donationservice.client.AssociationFeignClient;
import com.foodhero.donation.donationservice.client.UserFeignClient;
import com.foodhero.donation.donationservice.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

;

@Service
@RequiredArgsConstructor
public class DonationServiceService {

    private final UserFeignClient userFeignClient;
    private final AssociationFeignClient associationFeignClient;
    private final DonationRepository repository;

    public Donation saveDonation(Donation donation){
        return repository.save(donation);
    }

    public List<Donation> findAllDonations(){   return repository.findAll();
    }

    public List<Long> findDonationUsersByAssociationId(Long associationId) {
        List<Long> donationUserIds = new ArrayList<>();
        List<Donation> enrollments = repository.findAllByAssociationId(associationId);

        for (Donation enrollment : enrollments) {
            donationUserIds.add(enrollment.getUserId());
        }

        return donationUserIds;
    }

    public List<Long> findDonationAssociationsByUserId(Long userId) {
        List<Long> enrolledCommercantIds = new ArrayList<>();
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

    public List<Donation> findDonationsByUserId(Long userId) {
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

    public List<Donation> findDonationsByAssociationId(Long associationId) {
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
