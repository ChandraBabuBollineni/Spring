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
			            preprocessResponse(prettyPrint()),requestFields(fieldWithPath("id")
			                    .description("Auto generated ID for particular course.").attributes(key("required").value(false))
			            , fieldWithPath("name")
			                    .description("Unique name for particular course.").attributes(key("required").value(true))
			            , fieldWithPath("level.id")
			                    .description("level it belongs.").attributes(key("required").value(true))
			            , fieldWithPath("category.id")
			                    .description("to which category it belongs.").attributes(key("required").value(true))
			            , fieldWithPath("tags")
			                    .description("tags are useful for search purpose.").attributes(key("required").value(true))
			            , fieldWithPath("slug")
			                    .description("autogenerated unique slug for individual course").attributes(key("required").value(false))
			            , fieldWithPath("levelOverRide")
			                    .description("if you enable this lower level interns also can able to access this course").attributes(key("required").value(true))
			            , fieldWithPath("enrollmentPoint")
			                    .description("points to be awarded after successfully enrolled for particular course").attributes(key("required").value(true))
			            , fieldWithPath("completionPoint")
			                    .description("points to be awarded after successfully completition of particular course").attributes(key("required").value(true))
			            , fieldWithPath("preSignUp")
			                    .description("if you enable this, course is available before signup else available only in dashboard").attributes(key("required").value(true))
			            , fieldWithPath("loggedInViaSlug")
			                    .description("course is available to those who will login via slug url").attributes(key("required").value(true))
			            , fieldWithPath("description")
			                    .description("description for particular course").attributes(key("required").value(true))
			            , fieldWithPath("metaKeyword")
			              		.description("meta key words for particular course").attributes(key("required").value(true))    
			             , fieldWithPath("metaDescription")
			                    .description("meta description for particular course").attributes(key("required").value(true))
			              , fieldWithPath("referenceArtifactsId")
			                    .description("external reference artifacts for particular course can be added").attributes(key("required").value(true))
			              , fieldWithPath("referenceArtifactsId.[].id")
			                    .description("Auto generated reference artifacts ID for particular course").attributes(key("required").value(true))
			              , fieldWithPath("referenceArtifactsId.[].name")
			                    .description("name for a particular reference artifact").attributes(key("required").value(true))
			               , fieldWithPath("referenceArtifactsId.[].type")
			                    .description("reference artifact type").attributes(key("required").value(true))
			               , fieldWithPath("referenceArtifactsId.[].courseId")
			                    .description("Auto generated course ID for particular course").attributes(key("required").value(true))
			               , fieldWithPath("referenceArtifactsId.[].fileName")
			                    .description("name of the added reference artifact file").attributes(key("required").value(true))
			               , fieldWithPath("referenceArtifactsId.[].description")
			                    .description("description for particular reference artifact").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].id")
			                    .description("Auto generated reference URL ID for particular course").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].name")
			                    .description("name of the added reference URL").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].type")
			                    .description("reference URL type").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].courseId")
			                    .description("Auto generated ID for particular course").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].url")
			                    .description("refernced Url for particular course").attributes(key("required").value(true))
			               , fieldWithPath("refernceUrlId.[].tutorial")
			                    .description("is it tutorial or URL Link").attributes(key("required").value(true))			                   		       
			               , fieldWithPath("refernceUrlId.[].description")
			                    .description("description for particular reference URL").attributes(key("required").value(true))
			               , fieldWithPath("createdBy.id")
			                    .description("course created user id").attributes(key("required").value(true))
			               , fieldWithPath("createdBy.name")
			                    .description("course created user name").attributes(key("required").value(true))
			               , fieldWithPath("createdBy.email")
			                    .description("course created user email").attributes(key("required").value(true))
			               , fieldWithPath("icon")
			                    .description("icon to be displayed for this course").attributes(key("required").value(true))
			               , fieldWithPath("iconName")
			                    .description("displayed icon name").attributes(key("required").value(true))
			               , fieldWithPath("status")
			                    .description("is course active or de-active").attributes(key("required").value(false))
			               , fieldWithPath("version")
			                    .description("version of a particular course").attributes(key("required").value(false))	                    
						)));
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
		when(courseServiceMock.viewCourses(anyString(), anyString(),anyInt(),anyInt())).thenThrow(new ServiceException("No records available."));
		mockMvc.perform(get("/course?maxRows=2&orderBy=c.id&sortBy=asc&startIndex=0").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andDo(document("CourseController/view-courses-Invalid", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}	

	@Test
	public void testViewCourseById() throws Exception {
		CourseDTO course=new CourseDTO();
		course.setId(1);
		course.setName("java");
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON))
		.andDo(print()).andDo(document("CourseController/view-courses-by-id", preprocessRequest(prettyPrint()),
	            preprocessResponse(prettyPrint())));
	}

	@Test
	public void testViewCourseByIdInvalid() throws Exception {
		CourseDTO course = null;
		when(courseServiceMock.viewCourseById(anyInt())).thenReturn(course);
		mockMvc.perform(get("/course/1").contentType(MediaType.APPLICATION_JSON))
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
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(true);
		mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andDo(document("CourseController/delete-course-by-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
	
	@Test
	public void testDeleteCourseByIdInvalid() throws Exception {
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		mockMvc.perform(delete("/course/1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andDo(document("CourseController/delete-course-by-invalid-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
	
	@Test
	public void testDeleteCourseByIdInvalidId() throws Exception {
		when(courseServiceMock.deleteCourseById(anyInt())).thenReturn(false);
		mockMvc.perform(delete("/course/").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andDo(document("CourseController/delete-course-by-null-id", preprocessRequest(prettyPrint()),
			            preprocessResponse(prettyPrint())));
	      }
}
