package ca.vanier.vanierapi_lib.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.vanierapi_lib.entities.Student;
//Create the repository for student entity
@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
    
}
