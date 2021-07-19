package com.springrest.springrest.controller;

 
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dto.CourseDetailsDto;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.dto.CoursePlatformsDto;
import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.CourseDetails;
import com.springrest.springrest.entities.CoursePlatforms;
//import com.springrest.springrest.exception.ResourceNotFoundException;
import com.springrest.springrest.services.CourseService;


@RestController 
public class MyController {
	
	Logger log = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	public CourseService courseservice;
	
	@Autowired
	private CourseDao coursedao;
	
		
	//To get the courses
	@GetMapping("/courses")
	public List<CourseDto> getCourses()
	{
		//return this.coursedao.findAll();
		return this.courseservice.getCourses();
	}
	
//	@GetMapping("/courses/{courseId}")
//	public CourseDto getCourse(@PathVariable String courseId)
//	{
//		return courseservice.getCourse(Long.parseLong(courseId));
//	}
	
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable String courseId)
	{
		return courseservice.getCourse(Long.parseLong(courseId));
	}
	
	
	@PostMapping("/courses")
	public CourseDto addCourse(@Valid @RequestBody CourseDto coursedto)
	{
		return this.courseservice.addCourse(coursedto);
	
	}
	
	@PutMapping("/courses/{courseId}")
	public CourseDto updateCourse(@RequestBody CourseDto coursedto,@PathVariable String courseId)
	{
		return this.courseservice.updateCourse(coursedto,Long.parseLong(courseId));
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId)
	{
		try
		{
		 	 this.courseservice.deleteCourse(Long.parseLong(courseId));
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* CourseDetails Controller **/
	
	@PutMapping("/courses/details/{coursedetailsId}")
	public CourseDetails updateCourseDetails(@RequestBody CourseDetails courseDetails,
											@PathVariable String coursedetailsId)
	{
		return this.courseservice.updateCourseDetails(courseDetails,Long.parseLong(coursedetailsId));
	}
	
	@GetMapping("courses/details/{coursedetailsId}")
	public CourseDetailsDto getCourseDetails(@PathVariable String coursedetailsId)
	{
		return courseservice.getCourseDetails(Long.parseLong(coursedetailsId));
	}
	
	@GetMapping("/course/details")
	public List<CourseDetailsDto> getCoursesDetails()
	{
		return this.courseservice.getCoursesDetails();
		//return this.courseservice.getCourses();
	}
	
	/*** Course Platforms Controller **********/
	
	@GetMapping("/course/courseplatforms")
	public List<CoursePlatformsDto> getCoursePlatforms()
	{
		return this.courseservice.getCoursePlatforms();
		//return this.courseservice.getCourses();
	}
	
	@GetMapping("course/courseplatforms/{courseId}")
	public CoursePlatformsDto getCoursePlatformsById(@PathVariable String courseId)
	{
		return courseservice.getCoursePlatformsById(Long.parseLong(courseId));
	}
	
	@PutMapping("course/courseplatforms/{courseplatformId}")
	public CoursePlatforms updateCoursePlatforms(@Valid @RequestBody CoursePlatforms coursePlatforms,
											@PathVariable String courseplatformId)
	{
		return this.courseservice.updateCoursePlatforms(coursePlatforms,Long.parseLong(courseplatformId));
	}
	
	/** fetching details using course id ***/
	
	@GetMapping("/course/{courseId}/courseplatforms")
	public List<CoursePlatformsDto> getAllCoursePlatformsByDetailsId(@PathVariable String courseId) {
		return this.courseservice.getAllCoursePlatformsByDetailsId(Long.parseLong(courseId));

	}
	
	@PutMapping("/course/{courseId}/courseplatforms")
	public List<CoursePlatforms> updateCoursePlatformsByCourseId(@RequestBody List<CoursePlatforms> course,
			@PathVariable String courseId) {
		return this.courseservice.updateCoursePlatformsByCourseId(course,Long.parseLong(courseId));
	}
	
	/** Query Parameter *****/
	@GetMapping("/course/{courseId}/courseplatform")
	public List<CoursePlatforms> getCoursePlatformParam(@PathVariable String courseId,
														@RequestParam(value="id") String platformId)
	{
		return courseservice.getCoursePlatformParam(Long.parseLong(courseId),Long.parseLong(platformId));
	}
	
//	@GetMapping("/customers/{customerId}/contacts/{contactId}")
//	public ResponseEntity<ContactDTO> getContact(@PathVariable int customerId, @PathVariable int contactId){
//		return ResponseEntity.ok().body(contactService.getContact(customerId, contactId));
//	

}
