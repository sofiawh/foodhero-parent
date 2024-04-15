package com.foodhero.donation.donationservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.foodhero.donation.donationservice.client.AnnonceFeignClient;
import com.foodhero.donation.donationservice.client.AssociationFeignClient;
import com.foodhero.donation.donationservice.client.UserFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DonationServiceServiceTest {

    @Mock
    private DonationRepository donationRepository;

    @Mock
    private UserFeignClient userFeignClient;

    @Mock
    private AssociationFeignClient associationFeignClient;

    @Mock
    private AnnonceFeignClient annonceFeignClient;

    @InjectMocks
    private DonationServiceService donationService;

    @Test
    void testFindDonationsByUserId_WithValidUserId_ShouldReturnListOfDonations() {
        // Arrange
        String userId = "1";
        List<Donation> expectedDonations = new ArrayList<>();
        expectedDonations.add(new Donation());
        when(donationRepository.findAllByUserId(userId)).thenReturn(expectedDonations);

        // Act
        List<Donation> actualDonations = donationService.findDonationsByUserId(userId);

        // Assert
        assertEquals(expectedDonations.size(), actualDonations.size());
        // Add more assertions as needed
    }

    // Add more test methods for other service methods
}

