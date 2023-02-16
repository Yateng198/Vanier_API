package ca.vanier.vanier_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.course_lib.entity.Course;
import ca.vanier.course_lib.repository.CourseRepository;
import ca.vanier.student_lib.entity.Student;
import ca.vanier.student_lib.repository.StudentRepository;
import ca.vanier.teacher_lib.entity.Teacher;
import ca.vanier.teacher_lib.repository.TeacherRepository;

@Service
public class VanierServiceImpl implements VanierService{
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

    @Override
    public String registerStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();
        course.getStudents().add(student);
        courseRepository.save(course);
        return "Student: " + student.getFName() + " has been registed to course: " + course.getCourseName();
    }

    @Override
    public String assignTeacher(Long courseId, Long teacherId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        course.setTeacher(teacher);
        courseRepository.save(course);
        return "The teacher of course: " + course.getCourseName() + " has been changed to Dr. " + teacher.getFName();
    }

    @Override
    public String removeStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();
        course.removeStudent(student);
        courseRepository.save(course);

        return "Student: " + student.getFName() + " has been removed from course: " + course.getCourseName();
    }
    
}
