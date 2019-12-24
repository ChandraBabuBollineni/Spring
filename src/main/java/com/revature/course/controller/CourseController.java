package com.revature.course.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.course.configuration.Message;
import com.revature.course.dto.CourseDTO;
import com.revature.course.exception.ServiceException;
import com.revature.course.exception.ValidatorException;
import com.revature.course.services.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/***
 * 
 * course API, useful to add course,view course by id,view all available courses,update a particular course using id and delete a particular course by id.
 *
 */
@RestController
@RequestMapping("/course")
public class CourseController {
	
	private CourseService courseServices;
	
	 @Autowired
	   public CourseController(CourseService courseServices) {
	      this.courseServices = courseServices;
	   }

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
/***
 * 
 * URL is  /course of post type
 * @param courseDTO
 * @return Message
 */
	@PostMapping
	@ApiOperation("add course")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully added course", response = Message.class),
			@ApiResponse(code = 400, message = "unable to add course", response = Message.class) })
	public ResponseEntity<Object> addCourse(@RequestBody CourseDTO courseDTO) {
		try {
			boolean status = courseServices.addCourse(courseDTO);
			if (status) {
				Message message = new Message();
				message.setInfoMessage("course successfully added");
				message.setStatus("success");
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to add course");
				message.setStatus("failed");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch ( ServiceException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ValidatorException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
/***
 * 
 * @param orderBy - order by a particular column like id,name etc
 * @param sortBy - either Ascending or Descending
 * @param maxRows - maximum number of rows to fetch
 * @param startIndex - starting index of id
 * @return courseList
 */
	@GetMapping
	@ApiOperation("view courses")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "viewing available courses", response = Message.class),
			@ApiResponse(code = 400, message = "unable to view courses", response = Message.class) })
	public ResponseEntity<Object> viewCourse(@RequestParam("orderBy") String orderBy,@RequestParam("sortBy") String sortBy,@RequestParam("maxRows") int maxRows,@RequestParam("startIndex") int startIndex) {
		try {
			List<CourseDTO> coursesList = courseServices.viewCourses(orderBy,sortBy,maxRows,startIndex);
			Message message = new Message();
			message.setStatus("success");
			return new ResponseEntity<>(coursesList, HttpStatus.OK);
		} catch ( ServiceException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} 
	}
/***
 * 
 * @param id - based on id it will return a particular course details
 * @return - a particular course
 */
	@GetMapping("/{id}")
	@ApiOperation("view course by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "viewing available course by id", response = Message.class),
			@ApiResponse(code = 400, message = "unable to view course by id", response = Message.class) })
	public ResponseEntity<Object> viewCourseById(@PathVariable("id") int id) {
		try {
			CourseDTO course = courseServices.viewCourseById(id);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch ( ServiceException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} 
	}
/***
 * 
 * @param courseDTO
 * @return Message
 */
	@PutMapping
	@ApiOperation("update course")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully updated course", response = Message.class),
			@ApiResponse(code = 400, message = "unable to update course", response = Message.class) })
	public ResponseEntity<Object> updateCourse(@RequestBody CourseDTO courseDTO) {
		try {
			boolean status = courseServices.updateCourse(courseDTO); 
			if (status) {
				Message message = new Message();
				message.setInfoMessage("successfully updated course");
				message.setStatus("success");
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to update course");
				message.setStatus("failed");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch ( ServiceException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ValidatorException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
/***
 * 
 * @param id
 * @return Message
 */
	@DeleteMapping("/{id}")
	@ApiOperation("delete course")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully deleted course", response = Message.class),
			@ApiResponse(code = 400, message = "unable to delete course", response = Message.class) })
	public ResponseEntity<Object> deleteCourseById(@PathVariable("id") int id) {
		try {
			boolean status = courseServices.deleteCourseById(id);
			if (status) {
				Message message = new Message();
				message.setInfoMessage("successfully deleted course");
				message.setStatus("success");
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to delete course");
				message.setStatus("failed");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch ( ServiceException e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			message.setStatus("failed");
			return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}
}
