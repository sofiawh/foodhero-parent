package com.foodhero.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByCommercantId(Long commercantId);
    List<Enrollment> findAllByUserId(Long userId);
}
