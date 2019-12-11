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
		CourseDTO course = CourseDTOUtil.getCourseDTODetails();
		boolean result = false;
		Mockito.when(courseDAO.addCourse(course)).thenReturn(1);
		result = courseService.addCourse(course);
		assertEquals(true, result);
	}

	@Test
	public void testViewCourses() throws ServiceException {
		List<CourseDTO> courseList = new ArrayList<CourseDTO>();
		CourseDTO course = CourseDTOUtil.getCourseDTODetails();
		courseList.add(course);
		Mockito.when(courseDAO.viewCourses(anyString(), anyString(), anyInt(), anyInt())).thenReturn(courseList);
		List<CourseDTO> courses = null;
		courses = courseService.viewCourses("c.id", "desc", 2, 0);
		assertNotNull(courses);
	}

	@Test
	public void testViewCoursesInvalid() throws ServiceException {
		Throwable exception = assertThrows(ServiceException.class, () -> {
			List<CourseDTO> courseList = new ArrayList<CourseDTO>();
			Mockito.when(courseDAO.viewCourses(anyString(), anyString(), anyInt(), anyInt())).thenReturn(courseList);
			courseService.viewCourses("c.id", "desc", 2, 0);
		});
		assertEquals("No records available.", exception.getMessage());
	}

	@Test
	public void testViewCourseById() throws ServiceException {
		CourseDTO course = CourseDTOUtil.getCourseDTODetails();
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		CourseDTO courses = courseService.viewCourseById(9);
		assertNotNull(courses);
	}

	@Test
	public void testViewCourseByIdInvalid() {
		Throwable exception = assertThrows(ServiceException.class, () -> {
			CourseDTO course = null;
			Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
			courseService.viewCourseById(9);
		});
		assertEquals("course not available", exception.getMessage());
	}

	@Test
	public void testDeleteCourseById() throws ServiceException {
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(true);
		boolean result = courseService.deleteCourseById(9);
		assertTrue(result);
	}

	@Test
	public void testDeleteCourseByIdInvalid() throws ServiceException {
		Mockito.when(courseDAO.deleteCourseById(anyInt())).thenReturn(false);
		boolean result = courseService.deleteCourseById(9);
		assertFalse(result);
	}

	@Test
	public void testAddCourseByNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = null;
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}

	@Test
	public void testAddCourseByInvalidName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setName(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}

	@Test
	public void testAddCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setLevel(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testAddCourseByInvalidLevelId() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setLevel(new LevelDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testAddCourseByNullCategory() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCategory(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testAddCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCategory(new CategoryDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(1);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidCompletionPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCompletionPoint(-100);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Completion Point.", exception.getMessage());
	}

	@Test
	public void testAddCourseCreatedByNullID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCreatedBy(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidCreatedByID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCreatedBy(new UsersDTO());
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidEnrollmentPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setEnrollmentPoint(-100);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid Enrollment Point.", exception.getMessage());
	}

	@Test
	public void testAddCourseInvalidTags() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setTags(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid tags.", exception.getMessage());
	}

	@Test
	public void testAddCourseNullReferenceArtifacts() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setReferenceArtifactsId(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testAddCourseNullReferenceUrl() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setRefernceUrlId(null);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testAddCourseReferenceUrlNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceUrlDTO> referenceUrlDTOList = new ArrayList<>();
			ReferenceUrlDTO referenceUrlDTO = new ReferenceUrlDTO();
			referenceUrlDTOList.add(referenceUrlDTO);
			course.setRefernceUrlId(referenceUrlDTOList);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid referenceurl name", exception.getMessage());
	}

	@Test
	public void testAddCourseReferenceArtifactNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceArtifactsDTO> referenceArtifactsDTOList = new ArrayList<>();
			ReferenceArtifactsDTO referenceArtifactsDTO = new ReferenceArtifactsDTO();
			referenceArtifactsDTOList.add(referenceArtifactsDTO);
			course.setReferenceArtifactsId(referenceArtifactsDTOList);
			Mockito.when(courseDAO.addCourse(any())).thenReturn(0);
			courseService.addCourse(course);
		});
		assertEquals("Invalid reference artifact name", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = null;
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid course details.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByInvalidName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setName(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Name.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByLevelAsNull() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setLevel(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByInvalidLevelId() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setLevel(new LevelDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Level Id.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNullCategory() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCategory(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseByNullCategoryID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCategory(new CategoryDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Category ID.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidCompletionPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCompletionPoint(-100);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Completion Point.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseCreatedByNullID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCreatedBy(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidCreatedByID() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setCreatedBy(new UsersDTO());
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid User ID in created by.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidEnrollmentPoint() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setEnrollmentPoint(-100);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid Enrollment Point.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseInvalidTags() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setTags(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid tags.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseNullReferenceArtifacts() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setReferenceArtifactsId(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseNullReferenceUrl() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			course.setRefernceUrlId(null);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Course content is required.", exception.getMessage());
	}

	@Test
	public void testUpdateCourseReferenceUrlNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceUrlDTO> referenceUrlDTOList = new ArrayList<>();
			ReferenceUrlDTO referenceUrlDTO = new ReferenceUrlDTO();
			referenceUrlDTOList.add(referenceUrlDTO);
			course.setRefernceUrlId(referenceUrlDTOList);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid referenceurl name", exception.getMessage());
	}

	@Test
	public void testUpdateCourseReferenceArtifactNullName() throws ServiceException, ValidatorException {
		Throwable exception = assertThrows(ValidatorException.class, () -> {
			CourseDTO course = CourseDTOUtil.getCourseDTODetails();
			List<ReferenceArtifactsDTO> referenceArtifactsDTOList = new ArrayList<>();
			ReferenceArtifactsDTO referenceArtifactsDTO = new ReferenceArtifactsDTO();
			referenceArtifactsDTOList.add(referenceArtifactsDTO);
			course.setReferenceArtifactsId(referenceArtifactsDTOList);
			Mockito.when(courseDAO.updateCourse(any())).thenReturn(0);
			courseService.updateCourse(course);
		});
		assertEquals("Invalid reference artifact name", exception.getMessage());
	}

	@Test
	public void testUpdateCourse() throws ServiceException, ValidatorException {
		CourseDTO course = CourseDTOUtil.getCourseDTODetails();
		Mockito.when(courseDAO.updateCourse(any())).thenReturn(1);
		Mockito.when(courseDAO.viewCourseById(anyInt())).thenReturn(course);
		boolean result = courseService.updateCourse(course);
		assertEquals(true, result);
	}

	
}
