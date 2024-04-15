package com.foodhero.donation.donationservice;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationWithAssociationsAndUsersAndAnnonces {
    private Donation donation;
    private User user;
    private Association association;
    private Annonce annonce;
}

