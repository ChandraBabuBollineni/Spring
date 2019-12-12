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
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ValidatorException;
import com.revature.course.services.impl.CourseServiceImpl;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseServiceImpl courseServiceMock;

	@InjectMocks
	CourseControllerTest courseControllerTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddCourseInvalid()  {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		try {
		when(courseServiceMock.addCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "");}
		catch(Exception e) {e.printStackTrace();}
	}
	
	@Test
	void testAddCoursevalid()  {
		
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		try {
		when(courseServiceMock.addCourse(any())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(course);
		MvcResult mvcResult =mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(HttpStatus.BAD_REQUEST.value(), status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "");}
		catch(Exception e) {e.printStackTrace();}
	}

	
	
	
	
	

	@Test
	void testViewCourse() throws Exception {
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
	void testViewCourseInvalid() throws Exception {
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
	void testViewCourseById() throws Exception {
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
	void testViewCourseByIdInvalid() throws Exception {
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
	void testUpdateCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteCourseById() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(true);
		String userJson = new ObjectMapper().writeValueAsString(result);
		MvcResult mvcResult=mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      String expected = "{\"infoMessage\":null,\"errorMessage\":\"successfully deleted course\"}";
	      assertEquals(expected.toString(), content);
	      }
	
	@Test
	void testDeleteCourseByIdInvalid() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(result);
		MvcResult mvcResult=mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      String expected = "{\"infoMessage\":null,\"errorMessage\":\"unable to delete course\"}";
	      assertEquals(expected.toString(), content);
	      }
	
	@Test
	void testDeleteCourseByIdInvalidId() throws Exception {
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
