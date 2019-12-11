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
import com.revature.course.dao.impl.ReferenceUrlDAOImpl;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.services.impl.ReferenceUrlServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReferenceUrlServiceTest {
	@InjectMocks
	private ReferenceUrlServiceImpl referenceUrlService;

	@Mock
	private ReferenceUrlDAOImpl referenceUrlDAOImpl;

	/*@Before
	public void setUp() {	
		 MockitoAnnotations.initMocks(this);
	}*/
	@Test
	public void testGetReferenceUrlById() {
		List<ReferenceUrlDTO> referenceUrlDTO=new ArrayList<ReferenceUrlDTO>();
		Mockito.when(referenceUrlDAOImpl.getReferenceUrlById(anyInt())).thenReturn(referenceUrlDTO);
		List<ReferenceUrlDTO> referenceArtifacts= referenceUrlDAOImpl.getReferenceUrlById(1);
		assertNotNull(referenceArtifacts);
	}
	@Test
	public void testGetReferenceUrlByIdInvalid() {
		List<ReferenceUrlDTO> referenceUrlDTO=null;
		Mockito.when(referenceUrlDAOImpl.getReferenceUrlById(anyInt())).thenReturn(referenceUrlDTO);
		List<ReferenceUrlDTO> referenceArtifacts= referenceUrlDAOImpl.getReferenceUrlById(1);
		assertNull(referenceArtifacts);
	}
}
