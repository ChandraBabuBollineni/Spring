package com.revature.course.junitTests.servicesTests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.course.dao.impl.CourseDAOImpl;
import com.revature.course.dto.CategoryDTO;
import com.revature.course.dto.CourseDTO;
import com.revature.course.dto.LevelDTO;
import com.revature.course.dto.ReferenceArtifactsDTO;
import com.revature.course.dto.ReferenceUrlDTO;
import com.revature.course.dto.UsersDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.junitTests.util.CourseDTOUtil;
import com.revature.course.junitTests.util.CourseUtil;
import com.revature.course.model.Course;
import com.revature.course.services.ReferenceArtifactsService;
import com.revature.course.services.ReferenceUrlService;
import com.revature.course.services.impl.CourseServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CourseServiceImplTest {
	@InjectMocks
	private CourseServiceImpl courseService;

	@Mock
	private CourseDAOImpl courseDAO;

	@Mock
	private ReferenceArtifactsService referenceArtifactsServices;

	@Mock
	private ReferenceUrlService referenceUrlServices;

	@Test
	public void testAddCourse() throws ServiceException, ValidatorException {
		CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
		boolean result = false;
		Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
		result = courseService.addCourse(courseDTO);
		assertEquals(true, result);
	}

	@Test
	public void testViewCourses() throws ServiceException {
		List<Course> courseList = new ArrayList<Course>();
		Course course = CourseUtil.getCourseDetails();
		courseList.add(course);
		Mockito.when(courseDAO.viewCourses(anyString(), anyString(), anyInt(), anyInt())).thenReturn(courseList);
		List<CourseDTO> courses = null;
		courses = courseService.viewCourses("c.id", "desc", 2, 0);
		assertNotNull(courses);
	}

	@Test
	public void testViewCoursesInvalid() throws ServiceException {
		Throwable exception = assertThrows(ServiceException.class, () -> {
			List<Course> courseList = new ArrayList<Course>();
			Mockito.when(courseDAO.viewCourses(anyString(), anyString(), anyInt(), anyInt())).thenReturn(courseList);
			courseService.viewCourses("c.id", "desc", 2, 0);
		});
		assertEquals("No records available.", exception.getMessage());
	}

	@Test
	public void testViewCourseById() throws ServiceException {
		Course course = CourseUtil.getCourseDetails();
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		CourseDTO courses = courseService.viewCourseById(9);
		assertNotNull(courses);
	}

	@Test
	public void testViewCourseByIdInvalid() {
		Throwable exception = assertThrows(ServiceException.class, () -> {
			Course course = null;
			Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
			courseService.viewCourseById(9);
		});
		assertEquals("course not available", exception.getMessage());
	}

	@Test
	public void testDeleteCourseById() throws ServiceException {
		Course course=CourseUtil.getCourseDetails();
		course.setId(12);
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(true);
		Mockito.when(courseDAO.viewCourseById((anyInt()))).thenReturn(course);	
		boolean result = courseService.deleteCourseById(12);
		assertTrue(result);
	}

	@Test
	public void testDeleteCourseByIdInvalid() throws ServiceException {
		Throwable exception = assertThrows(ServiceException.class, () -> {
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(false);
		boolean result = courseService.deleteCourseById(12);
		assertFalse(result);
		});
		assertEquals("course not available", exception.getMessage());
	}

	@Test
	public void testAddCourseByNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = null;
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}

	@Test
	public void testAddCourseByInvalidName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setName(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}

	@Test
	public void testAddCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setLevel(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testAddCourseByInvalidLevelId() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setLevel(new LevelDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testAddCourseByNullCategory() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCategory(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testAddCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCategory(new CategoryDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidCompletionPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCompletionPoint(-100);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Completion Point.", exception.getMessage());
	}

	@Test
	public void testAddCourseCreatedByNullID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCreatedBy(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidCreatedByID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCreatedBy(new UsersDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidEnrollmentPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setEnrollmentPoint(-100);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid Enrollment Point.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidTags() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setTags(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid tags.", exception.getMessage());
	}

	@Test
	public void testAddCourseNullReferenceArtifacts() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setReferenceArtifacts(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testAddCourseNullReferenceUrl() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setRefernceUrl(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testAddCourseReferenceUrlNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceUrlDTO> referenceUrlList = new ArrayList<>();
			ReferenceUrlDTO referenceUrl = new ReferenceUrlDTO();
			referenceUrlList.add(referenceUrl);
			course.setRefernceUrl(referenceUrlList);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid referenceurl name", exception.getMessage());
	}

	@Test
	public void testAddCourseReferenceArtifactNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceArtifactsDTO> referenceArtifactsList = new ArrayList<>();
			ReferenceArtifactsDTO referenceArtifacts = new ReferenceArtifactsDTO();
			referenceArtifactsList.add(referenceArtifacts);
			courseDTO.setReferenceArtifacts(referenceArtifactsList);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(courseDTO);
		});
		assertEquals("Invalid reference artifact name", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = null;
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByInvalidName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setName(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setLevel(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByInvalidLevelId() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setLevel(new LevelDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNullCategory() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCategory(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCategory(new CategoryDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidCompletionPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCompletionPoint(-100);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Completion Point.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseCreatedByNullID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCreatedBy(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidCreatedByID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setCreatedBy(new UsersDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidEnrollmentPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setEnrollmentPoint(-100);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid Enrollment Point.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidTags() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setTags(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid tags.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseNullReferenceArtifacts() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setReferenceArtifacts(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseNullReferenceUrl() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			courseDTO.setRefernceUrl(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseReferenceUrlNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceUrlDTO> referenceUrlList = new ArrayList<>();
			ReferenceUrlDTO referenceUrl = new ReferenceUrlDTO();
			referenceUrlList.add(referenceUrl);
			courseDTO.setRefernceUrl(referenceUrlList);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid referenceurl name", exception.getMessage());
	}

	@Test
	public void testUpdateCourseReferenceArtifactNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceArtifactsDTO> referenceArtifactsList = new ArrayList<>();
			ReferenceArtifactsDTO referenceArtifacts = new ReferenceArtifactsDTO();
			referenceArtifactsList.add(referenceArtifacts);
			courseDTO.setReferenceArtifacts(referenceArtifactsList);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(courseDTO);
		});
		assertEquals("Invalid reference artifact name", exception.getMessage());
	}

	@Test
	public void testUpdateCourse() throws ServiceException, ValidatorException {
		Course course = CourseUtil.getCourseDetails();
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		CourseDTO courseDTO = CourseDTOUtil.getCourseDTODetails();
		boolean result = courseService.updateCourse(courseDTO);
		assertEquals(true, result);
	}
}
