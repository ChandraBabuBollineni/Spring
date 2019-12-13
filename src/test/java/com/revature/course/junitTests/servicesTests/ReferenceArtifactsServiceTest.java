package com.revature.course.junitTests.servicesTests;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.course.dao.impl.ReferenceArtifactsDAOImpl;
import com.revature.course.model.ReferenceArtifacts;
import com.revature.course.services.impl.ReferenceArtifactsServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReferenceArtifactsServiceTest {
	@InjectMocks
	private ReferenceArtifactsServiceImpl referenceArtifactsService;

	@Mock
	private ReferenceArtifactsDAOImpl referenceArtifactsDAO;

/*	@Before
	public void setUp() {	
		 MockitoAnnotations.initMocks(this);
	}
	*/
	@Test
	public void testViewReferenceArtifactsById() {
		List<ReferenceArtifacts> referenceArtifacts =new ArrayList<>();	
		Mockito.when(referenceArtifactsDAO.viewReferenceArtifactsById(anyInt())).thenReturn(referenceArtifacts);
		referenceArtifacts= referenceArtifactsDAO.viewReferenceArtifactsById(1);
		assertNotNull(referenceArtifacts);
	}
	
	@Test
	public void testViewReferenceArtifactsByIdInvalid() {
		List<ReferenceArtifacts> referenceArtifacts=null;	
		Mockito.when(referenceArtifactsDAO.viewReferenceArtifactsById(anyInt())).thenReturn(referenceArtifacts);
		referenceArtifacts= referenceArtifactsDAO.viewReferenceArtifactsById(1);
		assertNull(referenceArtifacts);
	}

}
