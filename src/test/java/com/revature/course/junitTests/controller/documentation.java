package com.revature.course.junitTests.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.course.controller.CourseController;
import com.revature.course.dto.CourseDTO;
import com.revature.course.services.impl.CourseServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class documentation {
	@Rule
	  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	
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
		mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0").contentType(MediaType.APPLICATION_JSON).content(userJson))
		.andDo(print()).andDo(document("CourseController/viewCourse"));
		
	}	
}
