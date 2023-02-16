package ca.vanier.course_lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.course_lib.entity.Course;
import ca.vanier.course_lib.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course searchCourseById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course updateCourse(Course course, Long id) {
        Course courseDB = courseRepository.findById(id).get();
        if (courseDB != null) {
            courseDB.setTeacher(course.getTeacher());
            courseDB.setCourseName(course.getCourseName());
            courseDB.setStudents(course.getStudents());
        }
        return courseRepository.save(courseDB);
    }
    
}
