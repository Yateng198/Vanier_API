package ca.vanier.student_lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.student_lib.entity.Student;
import ca.vanier.student_lib.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> listStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student searchStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        Student studentDB = studentRepository.findById(id).get();
        if (studentDB != null) {
            studentDB.setFName(student.getFName());
            studentDB.setLName(student.getLName());
            studentDB.setEmail(student.getEmail());
        }
        return studentRepository.save(studentDB);
    }
    
}
