package ca.vanier.vanier_api.service;

public interface VanierService {
    //Register a student to a specific course
    String registerStudentToCourse(Long courseId, Long studentId);

    //Assign a teacher to a specific course
    String assignTeacher(Long courseId, Long teacherId);

    //Remove a student from a specific course
    String removeStudentFromCourse(Long courseId, Long studentId);
}
