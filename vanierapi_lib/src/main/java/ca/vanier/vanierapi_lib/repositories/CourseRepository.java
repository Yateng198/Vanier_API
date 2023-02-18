package ca.vanier.vanierapi_lib.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
//Create repository for course entity
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    Course findByCourseName(String courseName);

    List<Course> findByStudentsContaining(Student student);
    
}
