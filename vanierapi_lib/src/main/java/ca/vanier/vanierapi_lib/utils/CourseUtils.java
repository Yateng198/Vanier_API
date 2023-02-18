package ca.vanier.vanierapi_lib.utils;

import java.util.ArrayList;
import java.util.List;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.entities.Teacher;

public class CourseUtils {
    // Register a student to a specific course
    public static String registerStudentToCourse(Student student, Course course) {
        List<Student> studentsCopy;
        List<String> coursesEnrolledCopy;
        if (course.getStudents() == null) {
            studentsCopy = new ArrayList<>();
        } else {
            studentsCopy = new ArrayList<>(course.getStudents());
        }
        if (student.getCoursesEnrolled() == null) {
            coursesEnrolledCopy = new ArrayList<>();
        } else {
            coursesEnrolledCopy = new ArrayList<>(student.getCoursesEnrolled());
        }
        // Check if the course is already full
        if (studentsCopy.size() >= 30) {
            return "We are sorry, this course is already full for this session, please try again later!";
        }
        // Check if the student is already enrolled in the course
        if (studentsCopy.contains(student) || coursesEnrolledCopy.contains("Course Name: " + course.getCourseName())) {
            return "The student: " + student.getFName() + " is already enrolled with course " + course.getCourseName();
        }
        //Add course name into course enrolled list for this student, and add this student into the students list of this course
        studentsCopy.add(student);
        coursesEnrolledCopy.add("Course Name: " + course.getCourseName());
        course.setStudents(studentsCopy);
        student.setCoursesEnrolled(coursesEnrolledCopy);
        //Success message
        return "Student: " + student.getFName() + " has been registered to course: " + course.getCourseName()
                + " successfully!";
    }

    // Remove a student from a specific course
    public static String removeStudentFromCourse(Student student, Course course) {
        String courseName = "Course Name: " + course.getCourseName();
        if (student.getCoursesEnrolled() == null || !student.getCoursesEnrolled().contains(courseName)) {
            return "Sorry, " + student.getFName() + " is not enrolled in course " + course.getCourseName() + " yet!";
        }
        if(course.getStudents() == null || !course.getStudents().contains(student)){
            return "Sorry, " + student.getFName() + " is not enrolled in course " + course.getCourseName() + " yet!";
        }
        course.removeStudent(student);
        student.getCoursesEnrolled().remove(courseName);
        return "Student " + student.getFName() + " has been removed from course " + course.getCourseName() + " successfully!";
    }
    // Assign or change the teacher of a specific course
    public static void assignTeacher(Teacher teacher, Course course) {
        course.setTeacher(teacher);
    }
}
