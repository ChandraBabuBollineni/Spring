package com.revature.course.mockmvcTests.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.revature.course.dao.impl.CourseDAOImpl;
import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.dto.UsersDTO;
import com.revature.course.exception.DBException;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.model.Category;
import com.revature.course.model.Course;
import com.revature.course.services.ReferenceArtifactsService;
import com.revature.course.services.ReferenceUrlService;
import com.revature.course.services.impl.CourseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class CourseServiceImplTest {
	@InjectMocks
	private CourseServiceImpl courseService;

	@Mock
	private CourseDAOImpl courseDAO;
	
	@Mock
	private ReferenceArtifactsService referenceArtifactsServices;
	
	@Mock
	private ReferenceUrlService referenceUrlServices;

	@Before
	public void setUp() {	
		 MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddCourse() throws ServiceException, ValidatorException, DBException {
		CourseDTO course=new CourseDTO();
		course.setName("java");
		LevelDTO level=new LevelDTO();
		level.setId(1);
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO.setId(1);
		course.setCategory(categoryDTO);
		course.setLevel(level);
		course.setCompletionPoint(100);
		UsersDTO usersDTO=new UsersDTO();
		usersDTO.setId(1);
		usersDTO.setEmail("chandra@gmail.com");
		usersDTO.setName("chandra");
		course.setCreatedBy(usersDTO);
		course.setDescription("it is description");
		course.setEnrollmentPoint(100);
		course.setLevelOverRide(true);
		course.setLoggedInViaSlug(true);
		course.setPreSignUp(true);
		course.setMetaDescription("it is meta description");
		course.setMetaKeyword("it is meta keyword");
		course.setSlug("www.app.revature.com/courses/java");
		course.setStatus(true);
		course.setTags("java,java programming");
		List<ReferenceArtifactsDTO> referenceArtifactsDTOList=new ArrayList<>();
		ReferenceArtifactsDTO referenceArtifactsDTO=new ReferenceArtifactsDTO();
		referenceArtifactsDTO.setDescription("description for interfaces");
		referenceArtifactsDTO.setName("interfaces");
		referenceArtifactsDTO.setType("Main content");
		referenceArtifactsDTOList.add(referenceArtifactsDTO);
		course.setReferenceArtifacts(referenceArtifactsDTOList);
		referenceArtifactsDTO=new ReferenceArtifactsDTO();
		referenceArtifactsDTO.setDescription("description for polymorphism");
		referenceArtifactsDTO.setName("polymorphism");
		referenceArtifactsDTO.setType("Main content");
		referenceArtifactsDTOList.add(referenceArtifactsDTO);
		course.setReferenceArtifacts(referenceArtifactsDTOList);		
		List<ReferenceUrlDTO> ReferenceUrlDTOList=new ArrayList<>();
		ReferenceUrlDTO referenceUrlDTO=new ReferenceUrlDTO();
		referenceUrlDTO.setDescription("description for interfaces");
		referenceUrlDTO.setName("interfaces");
		referenceUrlDTO.setType("Main content");
		referenceUrlDTO.setTutorial(false);
		referenceUrlDTO.setUrl("www.revature.com/interfaces");
		ReferenceUrlDTOList.add(referenceUrlDTO);
		course.setReferenceArtifacts(referenceArtifactsDTOList);
		referenceUrlDTO=new ReferenceUrlDTO();
		referenceUrlDTO.setDescription("description for polymorphism");
		referenceUrlDTO.setName("polymorphism");
		referenceUrlDTO.setType("Main content");
		referenceUrlDTO.setTutorial(true);
		referenceUrlDTO.setUrl("www.revature.com/polymorphism");
		ReferenceUrlDTOList.add(referenceUrlDTO);
		course.setRefernceUrl(ReferenceUrlDTOList);
		course.setIconName("C:/Users/Revature1/Downloads/rev-logo-2.png");
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		assertEquals(true,result);
	}

	@Test
	void testViewCourses() throws ServiceException, DBException {
		List<Course> courseList=new ArrayList<Course>();
		Course course =new Course();
		course.setId(9);
		course.setName("name");
		Category category=new Category();
		category.setId(1);
		course.setCategory(category);
		courseList.add(course);
		Mockito.when(courseDAO.viewCourses(anyString(),anyString(),anyInt(),anyInt())).thenReturn(courseList);
		List<CourseDTO> courses= courseService.viewCourses("c.id","desc",2,0);
		assertNotNull(courses);
	}
	
	@Test
	void testViewCoursesInvalid() throws ServiceException {	
		Exception exception=assertThrows(ServiceException.class, () -> {
		List<Course> courseList=new ArrayList<Course>();
		Mockito.when(courseDAO.viewCourses(anyString(),anyString(),anyInt(),anyInt())).thenReturn(courseList);
		List<CourseDTO> courses= courseService.viewCourses("c.id","desc",2,0);
		});
		assertEquals("No records available.", exception.getMessage());
	}
	

	@Test
	void testViewCourseById() throws ServiceException, DBException {
		Course course=new Course();
		course.setId(9);
		course.setName("name");
		Category category=new Category();
		category.setId(1);
		course.setCategory(category);
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		CourseDTO courses= courseService.viewCourseById(9);
		assertNotNull(courses);
	}

	@Test
	void testViewCourseByIdInvalid() throws ServiceException {
		Exception exception=assertThrows(ServiceException.class, () -> {
		Course course=null;
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		CourseDTO courses= courseService.viewCourseById(9);
		});
		assertEquals("course not available", exception.getMessage());
	}
	
	@Test
	void testUpdateCourseByNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=null;
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}
	
	@Test
	void testUpdateCourseByInvalidName() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}
	
	@Test
	void testUpdateCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}
	
	@Test
	void testUpdateCourseByInvalid() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	void testUpdateCourseByLevelIdAsNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		course.setLevel(level);
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}
	@Test
	void testUpdateCourseByNullCategory() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		level.setId(1);
		course.setLevel(level);
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}
	@Test
	void testUpdateCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		level.setId(1);
		CategoryDTO categoryDTO=new CategoryDTO();
		course.setCategory(categoryDTO);
		course.setLevel(level);
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		boolean result= courseService.updateCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}
	
	@Test
	void testDeleteCourseById() throws ServiceException, DBException {
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(true);
		boolean result= courseService.deleteCourseById(9);
		assertTrue(result);
	}
	@Test
	void testDeleteCourseByIdInvalid() throws ServiceException, DBException {
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(false);
		boolean result= courseService.deleteCourseById(9);
		assertFalse(result);
	}
	
	@Test
	void testAddCourseByNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=null;
		Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}
	
	@Test
	void testAddCourseByInvalidName() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}
	
	@Test
	void testAddCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}
	
	@Test
	void testAddCourseByInvalid() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	void testAddCourseByLevelIdAsNull() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		course.setLevel(level);
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}
	@Test
	void testAddCourseByNullCategory() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		level.setId(1);
		course.setLevel(level);
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}
	@Test
	void testAddCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Exception exception=assertThrows(ValidatorException.class, () -> {
		CourseDTO course=new CourseDTO();
		course.setName("chandra");
		LevelDTO level=new LevelDTO();
		level.setId(1);
		CategoryDTO categoryDTO=new CategoryDTO();
		course.setCategory(categoryDTO);
		course.setLevel(level);
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		boolean result= courseService.addCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}
	
}
