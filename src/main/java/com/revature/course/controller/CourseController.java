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
import com.revature.course.services.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseService courseServices;

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

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
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to add course");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	@ApiOperation("view courses")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "viewing available courses", response = Message.class),
			@ApiResponse(code = 400, message = "unable to view courses", response = Message.class) })
	public ResponseEntity<Object> viewCourse(@RequestParam("orderBy") String orderBy,@RequestParam("sortBy") String sortBy,@RequestParam("maxRows") int maxRows,@RequestParam("startIndex") int startIndex) {
		try {
			List<CourseDTO> coursesList = courseServices.viewCourses(orderBy,sortBy,maxRows,startIndex);
			return new ResponseEntity<>(coursesList, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation("view course by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "viewing available course by id", response = Message.class),
			@ApiResponse(code = 400, message = "unable to view course by id", response = Message.class) })
	public ResponseEntity<Object> viewCourseById(@PathVariable("id") int id) {
		try {
			CourseDTO course = courseServices.viewCourseById(id);
			return new ResponseEntity<>(course, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

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
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to update course");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation("delete course")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully deleted course", response = Message.class),
			@ApiResponse(code = 400, message = "unable to delete course", response = Message.class) })
	public ResponseEntity<Object> deleteCourseById(@PathVariable("id") int id) {
		try {
			boolean status = courseServices.deleteCourseById(id);
			if (status) {
				Message message = new Message("successfully deleted course");
				return new ResponseEntity<>(message, HttpStatus.OK);
			} else {
				Message message = new Message("unable to delete course");
				return new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (Exception e) {
			Message message = new Message(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}
}
