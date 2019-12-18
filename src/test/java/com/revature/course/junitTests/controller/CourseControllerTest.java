package com.revature.course.junitTests.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.course.controller.CourseController;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ValidatorException;
import com.revature.course.junitTests.util.CourseDTOUtil;
import com.revature.course.services.impl.CourseServiceImpl;


@RunWith(MockitoJUnitRunner.Silent.class)
public class CourseControllerTest {
	private MockMvc mockMvc;

	@Mock
	private CourseServiceImpl courseServiceMock;

	@InjectMocks
	CourseController courseControllerTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(courseControllerTest).build();
	}
	
	@Test
	public void testAddCourseInvalid() throws Exception  {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "{\"infoMessage\":null,\"errorMessage\":\"Invalid Details.\",\"status\":400}");
	}
	
	@Test
	public void testAddCourseInvalid1() throws Exception  {	
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	 assertEquals(content, "{\"infoMessage\":null,\"errorMessage\":\"Invalid Details.\",\"status\":400}");
	}
	
	@Test
	public void testAddCourseValid() throws Exception  {	
		CourseDTO course=CourseDTOUtil.getCourseDTODetails();
		when(courseServiceMock.addCourse(any())).thenReturn(true);
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	 assertEquals( "{\"infoMessage\":\"course successfully added\",\"errorMessage\":null,\"status\":200}",content);
	}
	
	@Test
	public void testAddCourseInvalid2() throws Exception  {	
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	 assertEquals(content, "{\"infoMessage\":null,\"errorMessage\":\"unable to add course\",\"status\":400}");
	}

	@Test
	public void testViewCourse() throws Exception {
		List<CourseDTO> courseList=new ArrayList<>();
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		courseList.add(course);
		when(courseServiceMock.viewCourses(anyString(), anyString(),anyInt(),anyInt())).thenReturn(courseList);
		String userJson = new ObjectMapper().writeValueAsString(courseList);
		MvcResult mvcResult =mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertFalse(content.isEmpty());
	}	
	
	@Test
	public void testViewCourseInvalid() throws Exception {
		List<CourseDTO> courseList = null;
		when(courseServiceMock.viewCourses(anyString(), anyString(),anyInt(),anyInt())).thenReturn(courseList);
		String userJson = new ObjectMapper().writeValueAsString(courseList);
		MvcResult mvcResult =mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertTrue(content.isEmpty());
	}	

	@Test
	public void testViewCourseById() throws Exception {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult=mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, userJson);
	}

	@Test
	public void testViewCourseByIdInvalid() throws Exception {
		CourseDTO course = null;
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult=mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertTrue(content.length()==0);
	}
	
	@Test
	public void testUpdateCourse() throws Exception {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.updateCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(put("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.OK.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	 assertEquals(content, "{\"infoMessage\":null,\"errorMessage\":\"Invalid Details.\",\"status\":400}");
	}

	@Test
	public void testDeleteCourseById() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(true);
		String userJson = new ObjectMapper().writeValueAsString(result);
		MvcResult mvcResult=mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      String expected = "{\"infoMessage\":\"successfully deleted course\",\"errorMessage\":null,\"status\":200}";
	      assertEquals(expected.toString(), content);
	      }
	
	@Test
	public void testDeleteCourseByIdInvalid() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(result);
		MvcResult mvcResult=mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      String expected = "{\"infoMessage\":null,\"errorMessage\":\"unable to delete course\",\"status\":400}";
	      assertEquals(expected.toString(), content);
	      }
	
	@Test
	public void testDeleteCourseByIdInvalidId() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(result);
		MvcResult mvcResult=mockMvc.perform(delete("/course/").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(405, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      String expected = "";
	      assertEquals(expected.toString(), content);
	      }

}
