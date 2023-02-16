package ca.vanier.teacher_lib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ca.vanier.teacher_lib.entity.Teacher;


class TeacherLibApplicationTests {


	@Test
	public void testGetId(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com");
		assertEquals(1001,	 teacher.getId());
	}

	@Test
	public void testGetFirstName(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com");
		assertEquals("Nafi",	 teacher.getFName());
	}

	@Test
	public void testGetlastName(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com");
		assertEquals("Kaswer",	 teacher.getLName());
	}

	@Test
	public void testGetEmail(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com");
		assertEquals("nawser@email.com",	 teacher.getEmail());
	}

	@Test
	public void testEquals(){
		Teacher teacher = new Teacher((long) 1001, "Nafi", "Kaswer", "nawser@email.com");
		Teacher teacher1 = new Teacher((long) 1002, "Leo", "Masca", "leoM@email.com");
		assertEquals(false,	 teacher.equals(teacher1));
	}

	



}
