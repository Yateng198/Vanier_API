package ca.vanier.course_lib.service;

import java.util.List;

import ca.vanier.course_lib.entity.Course;

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
