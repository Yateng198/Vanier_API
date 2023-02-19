package ca.vanier.vanierapi_lib.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Teacher;
import ca.vanier.vanierapi_lib.repositories.CourseRepository;
import ca.vanier.vanierapi_lib.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
    // Basic CRUD operations for Teacher entity
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        List<String> newTeachercoursesTeaching = teacher.getCoursesTeaching();
        if (newTeachercoursesTeaching == null || newTeachercoursesTeaching.isEmpty()) {
            // If the teacher doesn't have any courses teaching information, just add into database
            return teacherRepository.save(teacher);
        } else {
            // Iterate through the teacher's course list and check if each course already
            // exists in the database
            List<String> copyTeaching = new ArrayList<>(newTeachercoursesTeaching);
            for (String course : copyTeaching) {
                // Extract the course name from the course string
                String courseName = course.split(": ")[1];

                // Check if the course already exists in the database
                Course existingCourse = courseRepository.findByCourseName(courseName);
                teacherRepository.save(teacher);
                if (existingCourse == null) {
                    // If the course doesn't exist in the database, create a new course and add it
                    // to the teacher's courses teaching list
                    Course newCourse = new Course();
                    newCourse.setCourseName(courseName);
                    newCourse.setTeacher(teacher);
                    courseRepository.save(newCourse);
                } else {
                    // If the course already exists in the database, update its teacher and add it
                    // to the teacher's courses teaching list
                    existingCourse.setTeacher(teacher);
                    courseRepository.save(existingCourse);
                    teacher.getCoursesTeaching().add(course);
                    // Check for other teachers current teaching list, if anyone is currently
                    // teaching this course, remove from his/her teaching list
                    for (Teacher otherTeacher : teacherRepository.findAll()) {
                        if (otherTeacher.getCoursesTeaching() != null && otherTeacher.getCoursesTeaching().size() > 0) {
                            if (otherTeacher.getCoursesTeaching().contains(course)) {
                                otherTeacher.getCoursesTeaching().remove(course);
                                teacherRepository.save(otherTeacher);
                            }
                        }
                    }
                }
            }
            // Save the updated teacher object to the database
            return teacherRepository.save(teacher);
        }
    }

    @Override
    public List<Teacher> listTeacher() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher searchTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new RuntimeException("Teahcer not found with ID: " + id + ", Check you ID and try again please!");
        }
    }

    @Override
    public String deleteTeacher(Long id) {
        // Before deleting a teacher, check if this teacher is currently teaching any
        // course
        Teacher teacher = teacherRepository.findById(id).get();
        List<Course> coursesList = (ArrayList<Course>) courseRepository.findAll();
        String head = "Sorry, this teacher is currently teaching the courses shown: ";
        String end = "\nPlease assign another teacher for all of the courses he/she is currently teaching and try again!";
        StringBuilder sb = new StringBuilder();
        for (Course course : coursesList) {
            if (course.getTeacher().equals(teacher)) {
                sb.append("\nCourse ID: " + course.getId() + ", Course Name: " + course.getCourseName());
            }
        }
        if (sb.length() == 0) {
            teacherRepository.deleteById(id);
            return "The teacher Dr. " + teacher.getFName() + "has been removed successfully!";
        } else {
            return head + sb.toString() + end;
        }
    }

    @Override
    public Teacher updaTeacher(Teacher teacher, Long id) {
        Teacher teacherDB = teacherRepository.findById(id).get();
        if (teacherDB != null) {
            teacherDB.setFName(teacher.getFName());
            teacherDB.setLName(teacher.getLName());
            teacherDB.setEmail(teacher.getEmail());
            teacherDB.setCoursesTeaching(teacher.getCoursesTeaching());
            List<String> newTeachercoursesTeaching = teacherDB.getCoursesTeaching();
            if (newTeachercoursesTeaching == null || newTeachercoursesTeaching.isEmpty()) {
                // If the teacher doesn't have any courses, just add into database
                return teacherRepository.save(teacherDB);
            } else {
                // Iterate through the teacher's course list and check if each course already
                // exists in the database
                List<String> copyTeaching = new ArrayList<>(newTeachercoursesTeaching);
                for (String course : copyTeaching) {
                    // Extract the course name from the course string
                    String courseName = course.split(": ")[1];

                    // Check if the course already exists in the database
                    Course existingCourse = courseRepository.findByCourseName(courseName);

                    if (existingCourse == null) {
                        // If the course doesn't exist in the database, create a new course and add it
                        // to the teacher's courses teaching list
                        Course newCourse = new Course();
                        teacherRepository.save(teacherDB);
                        newCourse.setCourseName(courseName);
                        newCourse.setTeacher(teacherDB);
                        courseRepository.save(newCourse);
                        teacherDB.getCoursesTeaching().add(course);
                    } else {
                        // If the course already exists in the database, update its teacher and add it
                        // to the teacher's courses teaching list
                        teacherRepository.save(teacherDB);
                        existingCourse.setTeacher(teacherDB);
                        courseRepository.save(existingCourse);
                        teacherDB.getCoursesTeaching().add(course);
                        // Check for other teachers current teaching list, if anyone is currently
                        // teaching this course, remove from his/her teaching list
                        for (Teacher otherTeacher : teacherRepository.findAll()) {
                            if (otherTeacher.getCoursesTeaching() != null
                                    && otherTeacher.getCoursesTeaching().size() > 0) {
                                if (otherTeacher.getCoursesTeaching().contains(course)) {
                                    otherTeacher.getCoursesTeaching().remove(course);
                                    teacherRepository.save(otherTeacher);
                                }
                            }
                        }
                    }
                }
            }
        }
        // Save the updated teacher object to the database
        return teacherRepository.save(teacherDB);
    }

}
