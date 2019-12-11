package com.revature.course.junitTests.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.revature.course.dto.CategoryDTO;
import org.junit.Test;


public class CategoryDTOTest {
	private CategoryDTO categoryDTO=new CategoryDTO();
	private CategoryDTO categoryDTO1=new CategoryDTO();
	@Test
	public void testHashCode() {
		assertTrue( categoryDTO.hashCode()==categoryDTO1.hashCode() );
	}

	@Test
	public void testGetId() {
		categoryDTO.setId(1);
		assertEquals(1,categoryDTO.getId());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(categoryDTO.equals(categoryDTO1));
	}


	@Test
	public void testToString() {
		System.out.println(categoryDTO.toString());
		System.out.println(categoryDTO1.toString());
		assertEquals(categoryDTO.toString(), categoryDTO1.toString());
		}

}
