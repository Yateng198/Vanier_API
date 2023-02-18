package ca.vanier.vanierapi_lib.services;

import java.util.List;

import ca.vanier.vanierapi_lib.entities.Course;

public interface CourseService {
    
    //Save a course into database
    Course saveCourse(Course course);

    //List all Courses
    List<Course> listCourses();

    //Find a specific course by course id
    Course searchCourseById(Long id);

    //Delete Specific course by id
    void deleteCourse(Long id);

    //Update specific course information by id
    Course updateCourse(Course course, Long id);
}
