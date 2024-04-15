package com.foodhero.enrollment;

;


import com.foodhero.enrollment.client.CommercantFeignClient;
import com.foodhero.enrollment.client.UserFeignClient;
import com.foodhero.enrollment.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final UserFeignClient userFeignClient;
    private final CommercantFeignClient commercantFeignClient;
    private final EnrollmentRepository repository;

    public UserProfile saveEnrollment(UserProfile enrollment){
        return repository.save(enrollment);
    }

    public List<UserProfile> findAllEnrollments(){   return repository.findAll();
    }

    public List<Long> findEnrolledUsersByCommercantId(Long commercantId) {
        List<Long> enrolledUserIds = new ArrayList<>();
        List<UserProfile> enrollments = repository.findAllByCommercantId(commercantId);

        for (UserProfile enrollment : enrollments) {
            enrolledUserIds.add(enrollment.getUserId());
        }

        return enrolledUserIds;
    }

    public List<Long> findEnrolledCommercantsByUserId(Long userId) {
        List<Long> enrolledCommercantIds = new ArrayList<>();
        List<UserProfile> enrollments = repository.findAllByUserId(userId);

        for (UserProfile enrollment : enrollments) {
            enrolledCommercantIds.add(enrollment.getCommercantId());
        }

        return enrolledCommercantIds;
    }

    public void deleteEnrollment(Long enrollmentId) {
        repository.deleteById(enrollmentId);
    }

    public UserProfile updateEnrollment(Long enrollmentId, UserProfile updatedEnrollment) {
        UserProfile existingEnrollment = repository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException("Enrollment non trouvé avec l'ID : " + enrollmentId));

        // Mettez à jour les attributs de l'enrollment existant avec les données de l'enrollment mis à jour
        existingEnrollment.setUserId(updatedEnrollment.getUserId());
        existingEnrollment.setCommercantId(updatedEnrollment.getCommercantId());

        return repository.save(existingEnrollment);
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
