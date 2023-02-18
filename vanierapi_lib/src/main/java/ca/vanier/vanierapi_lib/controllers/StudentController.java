package ca.vanier.vanierapi_lib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.services.StudentService;

@RestController
@RequestMapping("/vanier")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // Students CRUD operations
    @PostMapping("/student/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/student/update/{id}")
    public String updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        if (studentService.searchStudentById(id).equals(null)) {
            return "Student is not Exist!";
        }
        studentService.updateStudent(student, id);
        return "Information of student with ID " + id + " is updated successfully!";
    }

    @GetMapping("/student/list")
    public List<Student> listStudents() {
        return (List<Student>) studentService.listStudents();
    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        if (studentService.searchStudentById(id).equals(null)) {
            return "Student with id " + id + " is not Exist!";
        }
        studentService.deleteStudent(id);
        return "Student with ID " + id + " has been deleted successfully!";
    }

    @GetMapping("/student/search/{id}")
    public Student searchStudent(@PathVariable("id") Long id) {
        return studentService.searchStudentById(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body("Student information not complete, please complete All fields and try again!");
    }
}
