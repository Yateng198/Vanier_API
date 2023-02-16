package ca.vanier.course_lib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.course_lib.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    
}
