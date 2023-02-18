package ca.vanier.vanierapi_lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.entities.Teacher;
import ca.vanier.vanierapi_lib.utils.CourseUtils;

public class CourseUtilsTest {
    @Test
    // Test Assign or change teacher for a specific course
    public void testAssignTeacher() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            studentList.add(new Student());
        }
        Teacher teacher = new Teacher((long) 101, "Leo", "Masca", "leoM@email.com", new ArrayList<>());
        course.setId((long) 1001);
        course.setCourseName("Database");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        Teacher teacher1 = new Teacher((long) 101, "Remy", "Beaumont", "rebeau@email.com", new ArrayList<>());
        CourseUtils.assignTeacher(teacher1, course);
        assert (course.getTeacher().equals(teacher1));
    }

    // Testing to register a student into a course which is already having 30
    // students enrolled
    @Test
    public void testRegisterStudentToCourseMoreThan30() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            studentList.add(new Student());
        }
        Teacher teacher = new Teacher();
        course.setId((long) 1001);
        course.setCourseName("WebServices");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        String str = "We are sorry, this course is already full for this session, please try again later!";
        assert str.equals(CourseUtils.registerStudentToCourse(new Student(), course));
    }

    // Testing register a student who is already enrolled to a course
    @Test
    public void testRegisterStudentToCourseAlreadyEnrolled() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        Student student = new Student((long) 1001, "Annabelle", "Geng", "Annag@email.com", new ArrayList<>());
        studentList.add(student);
        Teacher teacher = new Teacher();
        course.setId((long) 1001);
        course.setCourseName("WebServices");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        Student student1 = course.getStudents().get(0);
        String str = "The student: " + student.getFName() + " is already enrolled with course "
                + course.getCourseName();
        assert str.equals(CourseUtils.registerStudentToCourse(student1, course));
    }

    // Testing normally register a student to a course
    @Test
    public void testRegisterStudentToCourseNormal() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            studentList.add(new Student());
        }
        Teacher teacher = new Teacher();
        course.setId((long) 1001);
        course.setCourseName("WebServices");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        Student student = new Student((long) 1001, "Annabelle", "Geng", "Annag@email.com", new ArrayList<>());
        String str = "Student: " + student.getFName() + " has been registered to course: " + course.getCourseName()
                + " successfully!";
        assert str.equals(CourseUtils.registerStudentToCourse(student, course));
    }

    // Testing normally to remove a student from a course
    @Test
    public void testRemoveStudentFromCourseNormal() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            studentList.add(new Student());
        }
        Student student = new Student((long) 1001, "Annabelle", "Geng", "Annag@email.com", new ArrayList<>(Arrays.asList("Course Name: WebServices")));
        studentList.add(student);
        Teacher teacher = new Teacher();
        course.setId((long) 1001);
        course.setCourseName("WebServices");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        course.addStudent(student);

        String str = "Student " + student.getFName() + " has been removed from course " + course.getCourseName()
                + " successfully!";
        assert str.equals(CourseUtils.removeStudentFromCourse(student, course));
    }
    // Testing to remove a student who is not enrolled yet from a course
    @Test
    public void testRemoveStudentFromCourseNotContains() {
        Course course = new Course();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            studentList.add(new Student());
        }
        Teacher teacher = new Teacher();
        course.setId((long) 1001);
        course.setCourseName("WebServices");
        course.setTeacher(teacher);
        course.setStudents(studentList);
        Student student = new Student((long) 1001, "Annabelle", "Geng", "Annag@email.com", new ArrayList<>(Arrays.asList("Course Name: WebServices")));
        String str = "Sorry, " + student.getFName() + " is not enrolled in course " + course.getCourseName() + " yet!";
        assert str.equals(CourseUtils.removeStudentFromCourse(student, course));
    }
}
