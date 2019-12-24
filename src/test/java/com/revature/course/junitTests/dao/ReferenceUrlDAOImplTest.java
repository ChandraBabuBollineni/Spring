package com.revature.course.junitTests.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.revature.course.dao.ReferenceUrlDAO;
import com.revature.course.dao.impl.ReferenceUrlDAOImpl;
import com.revature.course.exception.DBException;
import com.revature.course.model.ReferenceUrl;

public class ReferenceUrlDAOImplTest {
/* private ReferenceUrlDAO referenceUrlDAO=null;
	@Test
	public void testGetReferenceUrlByIdValid() throws DBException {
		referenceUrlDAO=new ReferenceUrlDAOImpl(null);
		List<ReferenceUrl> referenceUrlList=referenceUrlDAO.getReferenceUrlById(12);
		assertFalse(referenceUrlList.isEmpty());
	}
	@Test
	public void testGetReferenceUrlByIdInvalid() throws DBException {
		referenceUrlDAO=new ReferenceUrlDAOImpl(null);
		List<ReferenceUrl> referenceUrlList=referenceUrlDAO.getReferenceUrlById(1);
		assertTrue(referenceUrlList.isEmpty());
	}*/
}
