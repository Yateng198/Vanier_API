package ca.vanier.vanierapi_lib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.vanierapi_lib.entities.Teacher;
import ca.vanier.vanierapi_lib.services.TeacherService;

@RestController
@RequestMapping("/vanier")
public class TeacherController {
    
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    // Teachers CRUD operations
    @PostMapping("/teacher/save")
    public Teacher saveteacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PostMapping("/teacher/update/{id}")
    public String updateTeacher(@RequestBody Teacher teacher, @PathVariable("id") Long id) {
        if (teacherService.searchTeacherById(id).equals(null)) {
            return "Teacher with ID " + id + " is NOT Exist!";
        }
        teacherService.updaTeacher(teacher, id);
        return "Information of teacher with ID " + id + " is Updated successfully!";
    }

    @GetMapping("/teacher/list")
    public List<Teacher> listAllTeachers() {
        return (List<Teacher>) teacherService.listTeacher();
    }

    @DeleteMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Long id) {
        if (teacherService.searchTeacherById(id).equals(null)) {
            return "Teacher with ID " + id + " is NOT Exist!";
        }
        return teacherService.deleteTeacher(id);
        // return "Teacher with ID " + id + " has been deleted Successfully!";
    }

    @GetMapping("/teacher/search/{id}")
    public Teacher searchTeacher(@PathVariable("id") Long id) {
        return teacherService.searchTeacherById(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


}
