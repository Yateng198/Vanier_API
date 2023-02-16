package ca.vanier.student_lib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.student_lib.entity.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    
}
