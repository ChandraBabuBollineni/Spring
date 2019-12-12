package com.revature.course.junitTests.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;

import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.dao.impl.ReferenceUrlDAOImpl;
import com.revature.course.dto.ReferenceUrlDTO;

public class ReferenceUrlDAOImplTest {
private ReferenceUrlDAO referenceUrlDAO=null;
	@Test
	public void testGetReferenceUrlByIdValid() {
		referenceUrlDAO=new ReferenceUrlDAOImpl();
		List<ReferenceUrlDTO> referenceUrlDTOList=referenceUrlDAO.getReferenceUrlById(12);
		assertFalse(referenceUrlDTOList.isEmpty());
	}
	@Test
	public void testGetReferenceUrlByIdInvalid() {
		referenceUrlDAO=new ReferenceUrlDAOImpl();
		List<ReferenceUrlDTO> referenceUrlDTOList=referenceUrlDAO.getReferenceUrlById(1);
		assertTrue(referenceUrlDTOList.isEmpty());
	}
}
