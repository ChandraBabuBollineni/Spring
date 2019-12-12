package com.revature.course.junitTests.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.revature.course.model.Level;

public class LevelTest {
	private Level level=null;
	private Level level1=null;
	
	@Before
	public void before()
	{
		level=new Level();
		level1=new Level();
	}
	
	@Test
	public void testIsActive() {
		level.setActive(true);
		assertEquals(true, level.isActive());
	}

	@Test
	public void testHashCode() {
		assertTrue( level.hashCode()==level1.hashCode() );
	}

	@Test
	public void testGetId() {
		level.setId(1);
		assertEquals(1, level.getId());
	}

	@Test
	public void testEqualsObject() {
		 assertTrue(level.equals(level1));
	}

	@Test
	public void testToString() {
		assertEquals(level.toString(), level.toString());
	}
	
}
