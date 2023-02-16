package ca.vanier.student_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.vanier.student_lib.entity.Student;


class StudentLibApplicationTests {

	@Test
	public void testGetFName(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com");
		assertEquals("Yateng", student.getFName());
	}

	@Test
	public void testGetId(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com");
		assertEquals(1001, student.getId());
	}

	@Test
	public void testGetLName(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com");
		assertEquals("Geng", student.getLName());
	}

	@Test
	public void testGetEmail(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com");
		assertEquals("yt@gamil.com", student.getEmail());
	}

	@Test
	public void testEquals(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com");
		Student student1 = new Student((long) 1002, "Tommy", "Beauman", "tomB@gamil.com");
		assertEquals(false, student.equals(student1));
	}

}
