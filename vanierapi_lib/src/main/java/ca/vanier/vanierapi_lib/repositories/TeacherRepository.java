package ca.vanier.vanierapi_lib.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.vanierapi_lib.entities.Teacher;
//Create repository for teacher entity
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long>{
    
}
