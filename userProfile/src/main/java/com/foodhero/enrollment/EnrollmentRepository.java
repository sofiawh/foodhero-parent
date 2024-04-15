package com.foodhero.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<UserProfile, Long> {
    List<UserProfile> findAllByCommercantId(Long commercantId);
    List<UserProfile> findAllByUserId(Long userId);
}
