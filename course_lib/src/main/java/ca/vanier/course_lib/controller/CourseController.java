package ca.vanier.course_lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.course_lib.entity.Course;
import ca.vanier.course_lib.service.CourseService;

@RestController
@RequestMapping("/vanier")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Courses CRUD operation
    @PostMapping("/course/save")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/course/{id}")
    public String updateCourse(@RequestBody Course course, @PathVariable("id") Long id) {
        if (courseService.searchCourseById(id).equals(null)) {
            return "Course is not Exist!";
        }
        courseService.updateCourse(course, id);
        return "Course with ID " + id + " is updated successfully!";
    }

    @GetMapping("/course/list")
    public List<Course> listCourses() {
        return courseService.listCourses();
    }

    @DeleteMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        if (courseService.searchCourseById(id).equals(null)) {
            return "Course is not Exist!";
        }
        courseService.deleteCourse(id);
        return "Course with ID " + id + " is deleted successfully!";
    }

    @GetMapping("/course/search/{id}")
    public Course searchCourse(@PathVariable("id") Long id) {
        return courseService.searchCourseById(id);
    }
    
}
