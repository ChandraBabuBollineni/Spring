package com.revature.course.junitTests.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.course.dao.impl.ReferenceArtifactsDAOImpl;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.model.ReferenceArtifacts;

public class ReferenceArtifactsDAOImplTest {
	private ReferenceArtifactsDAOImpl referenceArtifactsDAOImpl=null;
	@Test
	public void testViewReferenceArtifactsByIdInvalid() {
		referenceArtifactsDAOImpl=new ReferenceArtifactsDAOImpl();
		List<ReferenceArtifacts> referenceArtifactsList=referenceArtifactsDAOImpl.viewReferenceArtifactsById(1);
		assertTrue(referenceArtifactsList.isEmpty());
	}
	
	@Test
	public void testViewReferenceArtifactsByIdValid() {
		referenceArtifactsDAOImpl=new ReferenceArtifactsDAOImpl();
		List<ReferenceArtifacts> referenceArtifactsList=referenceArtifactsDAOImpl.viewReferenceArtifactsById(12);
		System.out.println(referenceArtifactsList);
		assertFalse(referenceArtifactsList.isEmpty());
	}

}
