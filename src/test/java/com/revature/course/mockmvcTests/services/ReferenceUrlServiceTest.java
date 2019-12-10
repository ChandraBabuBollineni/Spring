package com.revature.course.mockmvcTests.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.course.dao.impl.ReferenceUrlDAOImpl;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.services.impl.ReferenceUrlServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
class ReferenceUrlServiceTest {
	@InjectMocks
	private ReferenceUrlServiceImpl referenceUrlService;

	@Mock
	private ReferenceUrlDAOImpl referenceUrlDAOImpl;

	@Before
	public void setUp() {	
		 MockitoAnnotations.initMocks(this);
	}
	@Test
	void testGetReferenceUrlById() {
		List<ReferenceUrlDTO> referenceUrlDTO=new ArrayList<ReferenceUrlDTO>();
		Mockito.when(referenceUrlDAOImpl.getReferenceUrlById(anyInt())).thenReturn(referenceUrlDTO);
		List<ReferenceUrlDTO> referenceArtifacts= referenceUrlDAOImpl.getReferenceUrlById(1);
		assertNotNull(referenceArtifacts);
	}
	@Test
	void testGetReferenceUrlByIdInvalid() {
		List<ReferenceUrlDTO> referenceUrlDTO=null;
		Mockito.when(referenceUrlDAOImpl.getReferenceUrlById(anyInt())).thenReturn(referenceUrlDTO);
		List<ReferenceUrlDTO> referenceArtifacts= referenceUrlDAOImpl.getReferenceUrlById(1);
		assertNull(referenceArtifacts);
	}
}
