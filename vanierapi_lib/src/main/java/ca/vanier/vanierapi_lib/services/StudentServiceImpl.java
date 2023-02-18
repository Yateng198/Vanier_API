package ca.vanier.vanierapi_lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.repositories.CourseRepository;
import ca.vanier.vanierapi_lib.repositories.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService{
    //Basic CRUD operations for student entity
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    

    @Override
    public Student saveStudent(Student student) {
        List<String> newStudentCoursesEnrolled = student.getCoursesEnrolled();
    
        if (newStudentCoursesEnrolled == null || newStudentCoursesEnrolled.isEmpty()) {
            // If the student doesn't have any courses, just add into database
            return studentRepository.save(student);
        } else {
            // Iterate through the student's course list and check if each course already
            // exists in the database
            List<String> copyEnrolled = new ArrayList<>(newStudentCoursesEnrolled);
            for (String course : copyEnrolled) {
                // Extract the course name from the course string
                String courseName = course.split(": ")[1];
                // Check if the course already exists in the database
                Course existingCourse = courseRepository.findByCourseName(courseName);
    
                if (existingCourse == null) {
                    // If the course doesn't exist in the database, clear the student's courses enrolled (ignored the wrong user input) list
                    // and save the updated student to the database
                    newStudentCoursesEnrolled.clear();
                    studentRepository.save(student);
                } else {
                    // If the course already exists in the database, add the student to the course's enrolled list
                    studentRepository.save(student);
    
                    List<Student> studentsEnrolled = existingCourse.getStudents();
                    if (studentsEnrolled == null) {
                        studentsEnrolled = new ArrayList<>();
                        existingCourse.setStudents(studentsEnrolled);
                    }
    
                    studentsEnrolled.add(student);
                    courseRepository.save(existingCourse);
                }
            }
            // Save the updated student object to the database
            return studentRepository.save(student);
        }
    }
    
    @Override
    public List<Student> listStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student searchStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        } else {
            throw new RuntimeException("Student not found with ID: " + id + ", Check you ID and try again please!");
        }
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Course> coursesEnrolled = courseRepository.findByStudentsContaining(student);
            //While deleting student, remove this student from all the courses this student currently enrolled
            for (Course course : coursesEnrolled) {
                course.getStudents().remove(student);
                courseRepository.save(course);
            }
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with ID: " + id + ", Check you ID and try again please!");
        }
    }
    

    @Override
    public Student updateStudent(Student student, Long id) {
        Student studentDB = studentRepository.findById(id).get();
        if (studentDB != null) {
            studentDB.setFName(student.getFName());
            studentDB.setLName(student.getLName());
            studentDB.setEmail(student.getEmail());
            studentDB.setCoursesEnrolled(student.getCoursesEnrolled());
            List<String> newStudentCoursesEnrolled = studentDB.getCoursesEnrolled();
    
            if (newStudentCoursesEnrolled == null || newStudentCoursesEnrolled.isEmpty()) {
                // If the student doesn't have any courses, just add into database
                return studentRepository.save(studentDB);
            } else {
                // Iterate through the student's course list and check if each course already
                // exists in the database
                List<String> copyEnrolled = new ArrayList<>(newStudentCoursesEnrolled);
                for (String course : copyEnrolled) {
                    // Extract the course name from the course string
                    String courseName = course.split(": ")[1];
                    // Check if the course already exists in the database
                    Course existingCourse = courseRepository.findByCourseName(courseName);
        
                    if (existingCourse == null) {
                        // If the course doesn't exist in the database, clear the student's courses enrolled (ignored the wrong user input) list
                        // and save the updated student to the database
                        newStudentCoursesEnrolled.clear();
                        studentRepository.save(studentDB);
                    } else {
                        // If the course already exists in the database, add the student to the course's enrolled list
                        studentRepository.save(studentDB);
        
                        List<Student> studentsEnrolled = existingCourse.getStudents();
                        if (studentsEnrolled == null) {
                            studentsEnrolled = new ArrayList<>();
                            existingCourse.setStudents(studentsEnrolled);
                        }
        
                        studentsEnrolled.add(studentDB);
                        courseRepository.save(existingCourse);
                    }
                }
            }
        }
         // Save the updated student object to the database
        return studentRepository.save(studentDB);
    }
    
}
