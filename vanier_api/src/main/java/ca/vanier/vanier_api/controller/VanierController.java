package ca.vanier.vanier_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.vanier_api.service.VanierService;

@RestController
@RequestMapping("/vanier")
public class VanierController {
    private VanierService vanierService;

    @Autowired
    public VanierController(VanierService vanierService) {
        this.vanierService = vanierService;
    }

    //Register a student to a specific course
    @PostMapping("/register/{studentId}/{courseId}")
    public String registerStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        return vanierService.registerStudentToCourse(courseId, studentId);
    }
    //Assign a teacher to a specific course(like change the teacher)
    @PostMapping("/assign/{teacherId}/{courseId}")
    public String assignTeacher(@PathVariable Long courseId, @PathVariable Long teacherId) {
        return vanierService.assignTeacher(courseId, teacherId);
    }
    //Remove a student from a specific course
    @DeleteMapping("/remove/{studentId}/{courseId}")
    public String removeStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        return vanierService.removeStudentFromCourse(courseId, studentId);
    }
}
