package ca.vanier.vanier_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.entities.Teacher;
import ca.vanier.vanierapi_lib.repositories.CourseRepository;
import ca.vanier.vanierapi_lib.repositories.StudentRepository;
import ca.vanier.vanierapi_lib.repositories.TeacherRepository;
import ca.vanier.vanierapi_lib.utils.CourseUtils;

@Service
public class VanierServiceImpl implements VanierService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public VanierServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository,
            TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    // Register a student to a specific course
    @Override
    public String registerStudentToCourse(Long courseId, Long studentId) {
        Course course;
        Student student;
        try {
            // Retrieve the course from the database using the course ID
            course = courseRepository.findById(courseId).orElseThrow();
        } catch (Exception e) {
            // If no course is found, return an error message
            return "Error: We don't have a course with ID: " + courseId + ", check your ID and try again please!";
        }
        try {
            // Retrieve the student from the database using the student ID
            student = studentRepository.findById(studentId).orElseThrow();
        } catch (Exception e) {
            // If no student is found, return an error message
            return "Error: We don't have a Student with ID: " + studentId + ", check your ID and try again please!";
        }
        // Register the student to the course and get the corresponding message
        String registerStudentToCourse = CourseUtils.registerStudentToCourse(student, course);
        // Save the course entity to the database
        courseRepository.save(course);
        // Return the registration message
        return registerStudentToCourse;
    }

    // Assign or change teacher for a specific course
    @Override
    public String assignTeacher(Long courseId, Long teacherId) {
        Course course;
        Teacher teacher;
        try {
            course = courseRepository.findById(courseId).orElseThrow();
        } catch (Exception e) {
            return "Error: We don't have a course with ID: " + courseId + ", check your ID and try again please!";
        }
        try {
            teacher = teacherRepository.findById(teacherId).orElseThrow();
        } catch (Exception e) {
            return "Error: We don't have a Teacher with ID: " + teacherId + ", check your ID and try again please!";
        }
        // Remove the course name from the old teacher's coursesTeaching list
        Teacher oldTeacher = course.getTeacher();
        //Check if the old teacher is the same as the new teacher, if not the same, remove this course name from old teacher teaching list and save
        //Otherwise do not remove
        if (oldTeacher != null) {
            if(!teacher.equals(oldTeacher)){
                List<String> oldCoursesTeaching = oldTeacher.getCoursesTeaching();
                if (oldCoursesTeaching != null) {
                    String courseName = "Course Name: " + course.getCourseName();
                    oldCoursesTeaching.remove(courseName);
                    oldTeacher.setCoursesTeaching(oldCoursesTeaching);
                    teacherRepository.save(oldTeacher);
                }
            }
        }
        // Add the course name to the new teacher's coursesTeaching list
        List<String> coursesTeaching = teacher.getCoursesTeaching();
        if (coursesTeaching == null) {
            coursesTeaching = new ArrayList<>();
        }
        
        String courseName = "Course Name: " + course.getCourseName();
        //Check if this teacher is already teaching this course before add into the teaching list
        if (!coursesTeaching.contains(courseName)) {
            coursesTeaching.add(courseName);
        } else {
            return "Error: The teacher Dr. " + teacher.getFName() + " is already teaching Course:"
                    + course.getCourseName();
        }
        course.setTeacher(teacher);
        teacher.setCoursesTeaching(coursesTeaching);
        courseRepository.save(course);
        teacherRepository.save(teacher);

        return "The teacher of course: " + course.getCourseName() + " has been changed to Dr. " + teacher.getFName();
    }

    @Override
    public String removeStudentFromCourse(Long courseId, Long studentId) {
        Course course;
        Student student;
        try {
            course = courseRepository.findById(courseId).orElseThrow();
        } catch (Exception e) {
            return "Error: We don't have a course with ID: " + courseId + ", check your ID and try again please!";
        }
        try {
            student = studentRepository.findById(studentId).orElseThrow();
        } catch (Exception e) {
            return "Error: We don't have a Student with ID: " + studentId + ", check your ID and try again please!";
        }
        String removeStudentFromCourse = CourseUtils.removeStudentFromCourse(student, course);
        courseRepository.save(course);
        studentRepository.save(student);
        return removeStudentFromCourse;
    }

}
