package com.revature.course.junitTests.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.course.controller.CourseController;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.junitTests.util.CourseDTOUtil;
import com.revature.course.services.impl.CourseServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class NewDocumentation {
		@Rule
	  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	 private ObjectMapper objectMapper = new ObjectMapper();
	private MockMvc mockMvc;

	@Mock
	private CourseServiceImpl courseServiceMock;

	@InjectMocks
	CourseController courseControllerTest;

	 @Before
	  public void setUp() throws UnsupportedEncodingException {
	    this.mockMvc = MockMvcBuilders.standaloneSetup(courseControllerTest)
	        .apply(documentationConfiguration(this.restDocumentation).uris()
	            .withScheme("https").withHost("dev.chandra.com")
	            .withPort(443))
	        .build();
	    objectMapper.setSerializationInclusion(Include.NON_NULL);
	  }
	
	@Test
	public void testViewCourse() throws Exception {
		List<CourseDTO> courseList=new ArrayList<>();
		CourseDTO course=CourseDTOUtil.getCourseDTODetails();
		courseList.add(course);
		when(courseServiceMock.viewCourses(anyString(), anyString(),anyInt(),anyInt())).thenReturn(courseList);
		String userJson = new ObjectMapper().writeValueAsString(courseList);
		mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0"))
				.andDo(print()).andDo(document("CourseController/view-courses", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));		
	}	
	
	@Test
	public void testAddCourseInvalid() throws Exception  {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/add-course-Invalid-1", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}
	
	@Test
	public void testAddCourseInvalid1() throws Exception  {	
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/add-course-Invalid-2", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}
	
	@Test
	public void testAddCourseValid() throws Exception  {	
		CourseDTO course=CourseDTOUtil.getCourseDTODetails();
		when(courseServiceMock.addCourse(any())).thenReturn(true);
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andDo(print()).andDo(document("CourseController/add-course", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	}
	
	@Test
	public void testAddCourseInvalid2() throws Exception  {	
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.addCourse(any())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(post("/course").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/add-course-Invalid-3", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}

	
	@Test
	public void testViewCourseInvalid() throws Exception {
		List<CourseDTO> courseList = null;
		when(courseServiceMock.viewCourses(anyString(), anyString(),anyInt(),anyInt())).thenThrow(new ServiceException("No records available."));
		String userJson = new ObjectMapper().writeValueAsString(courseList);
		mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/view-courses-Invalid", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}	

	@Test
	public void testViewCourseById() throws Exception {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/view-courses-by-id", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint()), requestFields(fieldWithPath("id")
	                    .description("UserName of user").attributes(key("required").value(true)))));
	}

	@Test
	public void testViewCourseByIdInvalid() throws Exception {
		CourseDTO course = null;
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/view-courses-by-id-invalid", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}
	
	@Test
	public void testUpdateCourse() throws Exception {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.updateCourse(any())).thenThrow(new ValidatorException("Invalid Details."));
		String userJson = new ObjectMapper().writeValueAsString(course);
		mockMvc.perform(put("/course").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/update-course", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}

	@Test
	public void testDeleteCourseById() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(true);
		String userJson = new ObjectMapper().writeValueAsString(result);
		mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andDo(print()).andDo(document("CourseController/delete-course-by-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
	
	@Test
	public void testDeleteCourseByIdInvalid() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(result);
		mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andDo(print()).andDo(document("CourseController/delete-course-by-invalid-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
	
	@Test
	public void testDeleteCourseByIdInvalidId() throws Exception {
		Boolean result=true;
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		String userJson = new ObjectMapper().writeValueAsString(result);
		mockMvc.perform(delete("/course/").contentType(MediaType.APPLICATION_JSON).content(userJson))
				.andDo(print()).andDo(document("CourseController/delete-course-by-null-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
}
