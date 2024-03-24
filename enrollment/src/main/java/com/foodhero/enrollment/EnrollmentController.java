package com.foodhero.enrollment;


import com.foodhero.enrollment.client.CommercantFeignClient;
import com.foodhero.enrollment.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    @Autowired
    private EnrollmentService service;

    @Autowired
    private CommercantFeignClient commercantFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;


    @PostMapping
    public ResponseEntity<Enrollment> enrollUser(@RequestBody Enrollment enrollment) {
        // Validate student and course existence
        if (isValidUser(enrollment.getUserId()) && isValidCommercant(enrollment.getCommercantId())) {
            return ResponseEntity.ok(service.saveEnrollment(enrollment));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/commercants/{commercantId}/users")
    public ResponseEntity<List<Long>> getEnrolledUsersByCommercantId(@PathVariable Long commercantId) {
        List<Long> enrolledUserIds = service.findEnrolledUsersByCommercantId(commercantId);
        return ResponseEntity.ok(enrolledUserIds);
    }
    @GetMapping
    public ResponseEntity<List<Enrollment>> getEnrollments() {
        List<Enrollment> enrolleds = service.findAllEnrollments();
        return ResponseEntity.ok(enrolleds);
    }
    private boolean isValidUser(Long userId) {
        ResponseEntity<User> response = userFeignClient.getUserById(userId);
        return response.getStatusCode() == HttpStatus.OK;
    }

    private boolean isValidCommercant(Long commercantId) {
        ResponseEntity<Commercant> response = commercantFeignClient.getCommercantById(commercantId);
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
