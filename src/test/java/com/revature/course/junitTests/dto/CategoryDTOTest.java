package com.revature.course.junitTests.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.revature.course.dto.CategoryDTO;
import org.junit.Test;


public class CategoryDTOTest {
	private CategoryDTO categoryDTO=new CategoryDTO();
	@Test
	public void testHashCode() { 
		categoryDTO.setId(1);
		CategoryDTO categoryDTO1=new CategoryDTO();
		assertFalse( categoryDTO.hashCode()==categoryDTO1.hashCode());
	}

	@Test
	public void testGetId() {
		categoryDTO.setId(1);
		assertEquals(1,categoryDTO.getId());
	}

	@Test
	public void testEqualsObject() {
		CategoryDTO categoryDTO1=new CategoryDTO();
		 assertTrue(categoryDTO.equals(categoryDTO1));
	}


	@Test
	public void testToString() {
		CategoryDTO categoryDTO1=new CategoryDTO();
		assertEquals(categoryDTO.toString(), categoryDTO1.toString());
		
		}

}
