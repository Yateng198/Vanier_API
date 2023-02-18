package ca.vanier.vanierapi_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.vanier.vanierapi_lib.entities.Course;
import ca.vanier.vanierapi_lib.entities.Student;
import ca.vanier.vanierapi_lib.entities.Teacher;

class CourseJunitTests {

	//Testing get id method
	@Test
	public void testGetId(){
		Course course = new Course();
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		studentList.add(new Student((long)2,"Annabelle", "Geng","Annag@email.com", new ArrayList<>()));
		Teacher teacher = new  Teacher();
		
		course.setId((long)1001);
		course.setCourseName("Database");
		course.setTeacher(teacher);
		course.setStudents(studentList);
		// Course course = new Course((long)1001, "PC1",, studentList); 
		assertEquals(1001, course.getId());
	}
	//testing get course name method
	@Test
	public void testGetCourseName(){
		Course course = new Course();
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		studentList.add(new Student((long)2,"Annabelle", "Geng","Annag@email.com", new ArrayList<>()));
		Teacher teacher = new  Teacher((long) 101, "Yateng","geng","yt@email.com", new ArrayList<>());
		
		course.setId((long)1001);
		course.setCourseName("Database");
		course.setTeacher(teacher);
		course.setStudents(studentList);
		// Course course = new Course((long)1001, "PC1",, studentList); 
		assertEquals("Database", course.getCourseName());
	}

	//Testing add student method
	@Test
	public void testAddstudent(){
		Course course = new Course();
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		studentList.add(new Student((long)2,"Annabelle", "Geng","Annag@email.com", new ArrayList<>()));
		Teacher teacher = new  Teacher((long) 101, "Yateng","geng","yt@email.com", new ArrayList<>());
		
		course.setId((long)1001);
		course.setCourseName("Database");
		course.setTeacher(teacher);
		course.setStudents(studentList);
		Student student1 =  new Student((long)1,"Tom", "Beauman","tbeau@email.com", new ArrayList<>());
		course.addStudent(student1);

		List<Student> studentListNew = new ArrayList<>();
		studentListNew.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		assertFalse(studentList.equals(studentListNew));
	}

	//Testing remove student method
	@Test
	public void testRemoveStudent(){
		Course course = new Course();
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		studentList.add(new Student((long)2,"Annabelle", "Geng","Annag@email.com", new ArrayList<>()));
		Teacher teacher = new  Teacher();
		
		course.setId((long)1001);
		course.setCourseName("Database");
		course.setTeacher(teacher);
		course.setStudents(studentList);
		Student student1 =  new Student((long)2,"Annabelle", "Geng","Annag@email.com", new ArrayList<>());
		course.removeStudent(student1);

		List<Student> studentListNew = new ArrayList<>();
		studentListNew.add(new Student((long)1,"Michael", "Liu","ml@email.com", new ArrayList<>()));
		assertEquals(studentList, studentListNew);
	}

}
