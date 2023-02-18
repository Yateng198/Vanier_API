package ca.vanier.vanierapi_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.vanier.vanierapi_lib.entities.Teacher;


class TeacherJunitTests {

	//Testing get id method
	@Test
	public void testGetId(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com", new ArrayList<>());
		assertEquals(1001,	 teacher.getId());
	}
	//testing get first name method
	@Test
	public void testGetFirstName(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com", new ArrayList<>());
		assertEquals("Nafi",	 teacher.getFName());
	}

	@Test
	public void testGetlastName(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com",new ArrayList<>());
		assertEquals("Kaswer",	 teacher.getLName());
	}

	@Test
	public void testGetEmail(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com",new ArrayList<>());
		assertEquals("nawser@email.com",	 teacher.getEmail());
	}
	//testing equal method
	@Test
	public void testEquals(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com",new ArrayList<>());
		Teacher teacher1 = new Teacher((long) 1002, "Leo", "Masca", "leoM@email.com",new ArrayList<>());
		assertEquals(false,	 teacher.equals(teacher1));
	}

	



}
