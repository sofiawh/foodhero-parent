package com.foodhero.donation.donationservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class DonationControllerTest {

    @Mock
    private DonationServiceService donationService;

    @InjectMocks
    private DonationController donationController;

    @Test
    void testGetDonsByUserId_WithValidUserId_ShouldReturnListOfDonations() {
        // Arrange
        String userId = "1";
        List<Donation> expectedDonations = new ArrayList<>();
        expectedDonations.add(new Donation());
        when(donationService.findDonationsByUserId(userId)).thenReturn(expectedDonations);

        // Act
        ResponseEntity<List<Donation>> response = donationController.getDonsByUserId(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedDonations.size(), response.getBody().size());

    }

}

