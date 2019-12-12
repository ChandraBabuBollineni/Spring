package com.revature.course.junitTests.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.revature.course.dto.LevelDTO;

public class LevelDTOTest {
	private LevelDTO levelDTO=new LevelDTO();
	
	@Test
	public void testHashCode() {
		LevelDTO levelDTO1=new LevelDTO();
		assertTrue( levelDTO.hashCode()==levelDTO1.hashCode() );
	}

	@Test
	public void testGetId() {
		levelDTO.setId(1);
		assertEquals(1, levelDTO.getId());
	}

	@Test
	public void testEqualsObject() {
		LevelDTO levelDTO1=new LevelDTO();
		 assertTrue(levelDTO.equals(levelDTO1));
	}

	@Test
	public void testToString() {
		LevelDTO levelDTO1=new LevelDTO();
		System.out.println(levelDTO.toString());
		System.out.println(levelDTO1.toString());
		assertEquals(levelDTO.toString(), levelDTO.toString());
	}
}
