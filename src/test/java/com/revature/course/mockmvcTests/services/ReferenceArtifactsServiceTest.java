package com.revature.course.mockmvcTests.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.course.dao.impl.ReferenceArtifactsDAOImpl;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.services.impl.ReferenceArtifactsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReferenceArtifactsServiceTest {
	@InjectMocks
	private ReferenceArtifactsServiceImpl referenceArtifactsService;

	@Mock
	private ReferenceArtifactsDAOImpl referenceArtifactsDAO;

	@Before
	public void setUp() {	
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testViewReferenceArtifactsById() {
		List<ReferenceArtifactsDTO> referenceArtifactsDTO =new ArrayList<>();	
		Mockito.when(referenceArtifactsDAO.viewReferenceArtifactsById(anyInt())).thenReturn(referenceArtifactsDTO);
		List<ReferenceArtifactsDTO> referenceArtifacts= referenceArtifactsDAO.viewReferenceArtifactsById(1);
		assertNotNull(referenceArtifacts);
	}
	
	@Test
	void testViewReferenceArtifactsByIdInvalid() {
		List<ReferenceArtifactsDTO> referenceArtifactsDTO=null;	
		Mockito.when(referenceArtifactsDAO.viewReferenceArtifactsById(anyInt())).thenReturn(referenceArtifactsDTO);
		List<ReferenceArtifactsDTO> referenceArtifacts= referenceArtifactsDAO.viewReferenceArtifactsById(1);
		assertNull(referenceArtifacts);
	}

}
