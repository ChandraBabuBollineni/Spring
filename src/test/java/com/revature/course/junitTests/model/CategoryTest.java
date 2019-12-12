package com.revature.course.junitTests.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import com.revature.course.model.Category;

public class CategoryTest {
	private Category category=null;
	private Category category1=null;
	
	@Before
	public void before() {
		category =new Category();
		category1 =new Category();
	}
	
	@Test
	public void testHashCode() {
		category.setId(1);
		assertFalse(category.hashCode()==category1.hashCode());
	}

	@Test
	public void testGetId() {
		category.setId(1);
		assertEquals(1,category.getId());
	}

	@Test
	public void testGetName() {
		category.setName("JAVA");
		assertEquals("JAVA", category.getName());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(category.equals(category1));
	}

	@Test
	public void testToString() {
		assertEquals(category.toString(), category1.toString());
		}

	@Test
	public void testCategory() {
		category.setId(1);
		category.setName("JAVA");
		assertEquals(1,category.getId());
		assertEquals("JAVA", category.getName());
	}

}






















