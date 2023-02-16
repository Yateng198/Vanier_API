package ca.vanier.student_lib.service;

import java.util.List;

import ca.vanier.student_lib.entity.Student;

public interface StudentService {
    //Save a student into database
    Student saveStudent(Student student);

    //List all students
    List<Student> listStudents();

    //Find a specific student by student id
    Student searchStudentById(Long id);

    //Delete Specific student by id
    void deleteStudent(Long id);

    //Update specific student information by id
    Student updateStudent(Student student, Long id);

    
}
