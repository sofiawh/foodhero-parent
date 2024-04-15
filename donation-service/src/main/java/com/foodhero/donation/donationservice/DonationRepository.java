package com.foodhero.donation.donationservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByAssociationId(/*Long*/String associationId);
    List<Donation> findAllByUserId(/*Long*/String userId);
    List<Donation> findAllByAnnonceId(Long annonceId);
}
