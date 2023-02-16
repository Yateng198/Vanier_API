package ca.vanier.teacher_lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.teacher_lib.entity.Teacher;
import ca.vanier.teacher_lib.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> listTeacher() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher searchTeacherById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updaTeacher(Teacher teacher, Long id) {
        Teacher teacherDB = teacherRepository.findById(id).get();
        if (teacherDB != null) {
            teacherDB.setFName(teacher.getFName());
            teacherDB.setLName(teacher.getLName());
            teacherDB.setEmail(teacher.getEmail());
        }
        return teacherRepository.save(teacherDB);
    }
    
}
