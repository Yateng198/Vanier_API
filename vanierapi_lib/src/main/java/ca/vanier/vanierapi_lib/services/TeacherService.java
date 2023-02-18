package ca.vanier.vanierapi_lib.services;

import java.util.List;

import ca.vanier.vanierapi_lib.entities.Teacher;

public interface TeacherService {
    
    //Save a teacher into databse
    Teacher saveTeacher(Teacher teacher);

    //List all teachers
    List<Teacher> listTeacher();

    //Find a specific Teacher by teacher id
    Teacher searchTeacherById(Long id);

    //Delete Specific Teacher by id
    String deleteTeacher(Long id);

    //Update specific teacher information by id
    Teacher updaTeacher(Teacher teacher, Long id);
    
}
