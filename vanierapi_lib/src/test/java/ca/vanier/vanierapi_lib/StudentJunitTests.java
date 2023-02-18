package ca.vanier.vanierapi_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.vanier.vanierapi_lib.entities.Student;


class StudentJunitTests {
	//Testing get first name
	@Test
	public void testGetFName(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com", new ArrayList<>());
		assertEquals("Yateng", student.getFName());
	}
	//Testing get id
	@Test
	public void testGetId(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com", new ArrayList<>());
		assertEquals(1001, student.getId());
	}
	//Testing get last name
	@Test
	public void testGetLName(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com", new ArrayList<>());
		assertEquals("Geng", student.getLName());
	}
	//Testing get email
	@Test
	public void testGetEmail(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com", new ArrayList<>());
		assertEquals("yt@gamil.com", student.getEmail());
	}
	//Testing equals method
	@Test
	public void testEquals(){
		Student student = new Student((long) 1001, "Yateng", "Geng", "yt@gamil.com", new ArrayList<>());
		Student student1 = new Student((long) 1002, "Tommy", "Beauman", "tomB@gamil.com", new ArrayList<>());
		assertEquals(false, student.equals(student1));
	}

}
