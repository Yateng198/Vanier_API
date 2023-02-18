package ca.vanier.vanierapi_lib.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.entities.Teacher;
import ca.vanier.vanierapi_lib.repositories.CourseRepository;
import ca.vanier.vanierapi_lib.repositories.StudentRepository;
import ca.vanier.vanierapi_lib.repositories.TeacherRepository;

//Course sevice class, for CRUD operations
@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository,
            StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course saveCourse(Course course) {
        // Check if courseName already exists in database
        Course existingCourse = courseRepository.findByCourseName(course.getCourseName());
        if (existingCourse != null) {
            throw new RuntimeException("Course already exists, check your input and try again please!");
        }

        // Check if teacher is null or not existing
        Teacher teacher = teacherRepository.findById(course.getTeacher().getId()).get();
        if (teacher == null) {
            throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
        } else if (teacher.getId() == null) {
            throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
        } else if (teacher.getId() != null) {
            Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getId());
            if (!existingTeacher.isPresent()) {
                throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
            }
        }

        // Add course information into Teacher teaching courses list while adding a new
        // course
        List<String> coursesTeaching = teacher.getCoursesTeaching();
        if (coursesTeaching == null) {
            coursesTeaching = new ArrayList<>();
        }
        coursesTeaching.add("Course Name: " + course.getCourseName());
        teacher.setCoursesTeaching(coursesTeaching);
        teacherRepository.save(teacher);
        course.setTeacher(teacher);

        // Iterate through students and add course to their coursesEnrolled list
        List<Student> students = course.getStudents();
        if (students != null) {
            List<Student> studentsDB = new ArrayList<>();
            // for(Student stu: students){
            //     if(stu.getId() == null){
            //         throw new RuntimeException("Complete the Student information with an existing Student please!");
            //     }
            for(Student stu: students){
                Long studentId = stu.getId();
                if(studentId == null || !studentRepository.findById(studentId).isPresent()){
                    throw new RuntimeException("Invalid Student ID: " + studentId + ", Complete the Student information with an existing Student please!");
                }
                Student studentInDB = studentRepository.findById(stu.getId()).get();
                studentsDB.add(studentInDB);
            }
            for (Student student : studentsDB) {
                List<String> coursesEnrolled = student.getCoursesEnrolled();
                if (coursesEnrolled == null) {
                    coursesEnrolled = new ArrayList<>();
                }
                coursesEnrolled.add("Course Name: " + course.getCourseName());
                student.setCoursesEnrolled(coursesEnrolled);
                studentRepository.save(student);
            }
            course.setStudents(studentsDB);
        }

        return courseRepository.save(course);
    }

    @Override
    public List<Course> listCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course searchCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new RuntimeException("Course not found with ID: " + id + ", Check you ID and try again please!");
        }
    }

    @Override
    public void deleteCourse(Long id) {
        // Check if course exists
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found with ID: " + id + ", Check you ID and try again please!"));

        // Remove the course from the teacher's coursesTeaching list
        Teacher teacher = course.getTeacher();
        if (teacher != null) {
            List<String> coursesTeaching = teacher.getCoursesTeaching();
            if (coursesTeaching != null) {
                String courseName = "Course Name: " + course.getCourseName();
                coursesTeaching.remove(courseName);
                teacherRepository.save(teacher);
            }
        }

        // Remove the course from each student's coursesEnrolled list and save the
        // student
        List<Student> students = course.getStudents();
        if (students != null) {
            for (Student student : students) {
                List<String> coursesEnrolled = student.getCoursesEnrolled();
                if (coursesEnrolled != null) {
                    String courseName = "Course Name: " + course.getCourseName();
                    Iterator<String> iter = coursesEnrolled.iterator();
                    while (iter.hasNext()) {
                        String enrolledCourse = iter.next();
                        if (enrolledCourse.equals(courseName)) {
                            iter.remove();
                        }
                    }
                    studentRepository.save(student);
                }
            }
        }

        // Delete the course
        courseRepository.delete(course);
    }

    @Override
    public Course updateCourse(Course course, Long id) {
        Course courseDB = courseRepository.findById(id).get();
        String oldCourseName = "Course Name: " + courseDB.getCourseName();
        if (courseDB != null) {
            courseDB.setTeacher(course.getTeacher());
            courseDB.setCourseName(course.getCourseName());
            courseDB.setStudents(course.getStudents());
            Course existingCourse = courseRepository.findByCourseName(courseDB.getCourseName());
            if (existingCourse != null) {
                throw new RuntimeException("Course already exists, check your input and try again please!");
            }

            // Check if teacher is null or not existing
            Teacher teacher = courseDB.getTeacher();
            if (teacher == null) {
                throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
            } else if (teacher.getId() == null) {
                throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
            } else if (teacher.getId() != null) {
                Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getId());
                if (!existingTeacher.isPresent()) {
                    throw new RuntimeException("Complete the Teacher information with an existing teacher please!");
                }
            }

            // Add course information into Teacher teaching courses list if the teaching list is not contain this course name
            List<String> coursesTeaching = teacher.getCoursesTeaching();
            if (coursesTeaching == null) {
                coursesTeaching = new ArrayList<>();
            }
            String courseName = "Course Name: " + courseDB.getCourseName();
            if (!coursesTeaching.contains(courseName)) {
                coursesTeaching.add(courseName);
            }
            if(coursesTeaching.contains(oldCourseName)){
                coursesTeaching.remove(oldCourseName);
            }
            teacher.setCoursesTeaching(coursesTeaching);
            teacherRepository.save(teacher);
            // Iterate through students and add course to their coursesEnrolled list if they dont have this course name yet
            List<Student> students = courseDB.getStudents();
            if (students != null) {
                for (Student student : students) {
                    List<String> coursesEnrolled = student.getCoursesEnrolled();
                    if (coursesEnrolled == null) {
                        coursesEnrolled = new ArrayList<>();
                    }
                    if (!coursesEnrolled.contains(courseName)) {
                        coursesEnrolled.add(courseName);
                    }
                    if(coursesEnrolled.contains(oldCourseName)){
                        coursesTeaching.remove(oldCourseName);
                    }
                    student.setCoursesEnrolled(coursesEnrolled);
                    studentRepository.save(student);
                }
            }
        }
        return courseRepository.save(courseDB);
    }

}
