package ca.vanier.teacher_lib.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.teacher_lib.entity.Teacher;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>{
    
}
