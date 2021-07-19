package com.springrest.springrest.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.CourseDetailsDao;
import com.springrest.springrest.dao.CoursePlatformsDao;
import com.springrest.springrest.dto.CourseDetailsDto;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.dto.CoursePlatformsDto;
import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.CourseDetails;
import com.springrest.springrest.entities.CoursePlatforms;
import com.springrest.springrest.exception.ResourceNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

	// List<Course> list;
	@Autowired
	private CourseDao coursedao;
	
	@Autowired
	private FeignInterface feignInterface;

	@Autowired
	private CourseDetailsDao courseDetailsdao;

	@Autowired
	private CoursePlatformsDao coursePlatformsdao;

	ModelMapper modelmapper = new ModelMapper();

	@Override
	public List<CourseDto> getCourses() {
		// List<Course> course = this.coursedao.findAll();
		// return course;
		List<Course> course = coursedao.findAll();
		if (course.isEmpty()) {
			throw new ResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<CourseDto> courseResponse = course.stream().map(courses -> modelmapper.map(courses, CourseDto.class))
					.collect(Collectors.toList());
			return courseResponse;
		}
	}
	
	public Course create(Course course) {
		
		return coursedao.save(course);
		
	}

//	@Override
////	public CourseDto getCourse(long courseId) {
////
////		Optional<Course> courseDb = this.coursedao.findById(courseId);
////		if (courseDb.isPresent()) {
////			Course course = new Course();
////			course = courseDb.get();
////			CourseDto courseDto = modelmapper.map(course, CourseDto.class);
////			return courseDto;
////		} else {
////			throw new ResourceNotFoundException("course not found with id:" + courseId);
////		}
////		// return coursedao.findById(courseId).orElseThrow(()-> new
////		// ResourceNotFoundException("Course not found with id:"+courseId));
////		// return coursedao.getOne(courseId);
////
////	}

	@Override
	public CourseDto addCourse(CourseDto coursedto) {
		{
			Course course = modelmapper.map(coursedto,Course.class);

			String value=feignInterface.getDetails();
			course.setSample(value);
			coursedao.save(course);
			CourseDto courseDto = modelmapper.map(course, CourseDto.class);
			return courseDto;

		}
	}

	@Override
	public CourseDto updateCourse(CourseDto coursedto, long courseId) {

		Course course = modelmapper.map(coursedto,Course.class); 
		 course=coursedao.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course not found with id:"+courseId));;
		course.setTitle(course.getTitle());
		course.setDescription(course.getDescription());

//		existingCourse.getCourseDetails().setCourseDuration(course.getCourseDetails().getCourseDuration());
//		existingCourse.getCourseDetails().setCoursePrice(course.getCourseDetails().getCoursePrice());
//		existingCourse.getCourseDetails().setCourseInstructor(course.getCourseDetails().getCourseInstructor());
			// ((CoursePlatforms)
			// existingCourse.getCoursePlatforms()).setCoursePlatformName(((CoursePlatforms)course.getCoursePlatforms()).getCoursePlatformName());
			coursedao.save(course);
			CourseDto coursedtoo = modelmapper.map(course, CourseDto.class);
			return coursedtoo;

	}

	@Override
	public void deleteCourse(long parselong) {
		// Course entity=coursedao.getOne(parselong);
		Course entity = coursedao.findById(parselong)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + parselong));
		coursedao.delete(entity);

	}

	/* Course Details Service Implementation(One to One) */

	@Override
	public CourseDetails updateCourseDetails(CourseDetails courseDetails, long coursedetailsId) {
		CourseDetails existingDetails = courseDetailsdao.findById(coursedetailsId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + coursedetailsId));
		existingDetails.setCoursePrice(courseDetails.getCoursePrice());
		existingDetails.setCourseDuration(courseDetails.getCourseDuration());
		existingDetails.setCourseInstructor(courseDetails.getCourseInstructor());
		return courseDetailsdao.save(existingDetails);
	}

	@Override
	public CourseDetailsDto getCourseDetails(long coursedetailsId) {

		Optional<CourseDetails> courseDb = this.courseDetailsdao.findById(coursedetailsId);
		if (courseDb.isPresent()) {
			CourseDetails coursedetails = new CourseDetails();
			coursedetails = courseDb.get();
			CourseDetailsDto coursedetailsDto = modelmapper.map(coursedetails, CourseDetailsDto.class);
			return coursedetailsDto;
		} else {
			throw new ResourceNotFoundException("course not found with id:" + coursedetailsId);
		}

	}

	@Override
	public List<CourseDetailsDto> getCoursesDetails() {
		List<CourseDetails> coursedetails = courseDetailsdao.findAll();
		if (coursedetails.isEmpty()) {
			throw new ResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<CourseDetailsDto> courseResponse = coursedetails.stream().map(courses -> modelmapper.map(courses, CourseDetailsDto.class))
					.collect(Collectors.toList());
			return courseResponse;
		}
	}

	/* Course Platforms service implementation (One to Many) */

	@Override
	public List<CoursePlatformsDto> getCoursePlatforms() {
		List<CoursePlatforms> courseplatforms = coursePlatformsdao.findAll();
		if (courseplatforms.isEmpty()) {
			throw new ResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<CoursePlatformsDto> courseResponse = courseplatforms.stream().map(coursesplat -> modelmapper.map(coursesplat, CoursePlatformsDto.class))
					.collect(Collectors.toList());
			return courseResponse;
		}
	}

	@Override
	public CoursePlatformsDto getCoursePlatformsById(long courseplatformId) {

		Optional<CoursePlatforms> courseDb = this.coursePlatformsdao.findById(courseplatformId);
		if (courseDb.isPresent()) {
			CoursePlatforms courseplatforms = new CoursePlatforms();
			courseplatforms = courseDb.get();
			CoursePlatformsDto courseplatformsDto = modelmapper.map(courseplatforms, CoursePlatformsDto.class);
			return courseplatformsDto;
		} else {
			throw new ResourceNotFoundException("course not found with id:" + courseplatformId);
		}
	}

	@Override
	public CoursePlatforms updateCoursePlatforms(CoursePlatforms coursePlatforms, long courseplatformId) {

		return coursePlatformsdao.findById(courseplatformId).map(course -> {
			course.setCoursePlatformName(coursePlatforms.getCoursePlatformName());
			course.setCoursePlatformRating(coursePlatforms.getCoursePlatformRating());
			return coursePlatformsdao.save(course);
		}).orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + courseplatformId));
	}

	@Override
	public List<CoursePlatformsDto> getAllCoursePlatformsByDetailsId(long courseId) {
		Optional<Course> courseDb = this.coursedao.findById(courseId);
		if (courseDb.isPresent()) {
			Course courseUpdate = courseDb.get();
			List<CoursePlatforms> courseplatforms = courseUpdate.getCoursePlatforms();
			List<CoursePlatformsDto> courseResponse = courseplatforms.stream()
					.map(coursep -> modelmapper.map(coursep, CoursePlatformsDto.class)).collect(Collectors.toList());
			return courseResponse;

		} else {
			throw new ResourceNotFoundException("Record not found with id : " + courseId);

		}
	}

	@Override
	public List<CoursePlatforms> updateCoursePlatformsByCourseId(List<CoursePlatforms> course, long courseId) {
		Optional<Course> courseDb = coursedao.findById(courseId);
		if (courseDb.isPresent()) {
			Course newCourse = courseDb.get();
			newCourse.setCoursePlatforms(course);
			Course save = coursedao.save(newCourse);
			return save.getCoursePlatforms();
		}
		throw new ResourceNotFoundException("Record not found with id : " + courseId);
	}

	@Override
	public List<CoursePlatforms> getCoursePlatformParam(long courseId, long platformId) {
		Optional<Course> courseDb = coursedao.findById(courseId);
		if (courseDb.isPresent()) {
			Course course = courseDb.get();
			return course.getCoursePlatforms();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + courseId);

		}
	}
	
	public Course getCourse(long courseId) {

		Optional<Course> courseDb = this.coursedao.findById(courseId);
		if (courseDb.isPresent()) {
			Course course = new Course();
			course = courseDb.get();
			return course;
		} else {
			throw new ResourceNotFoundException("course not found with id:" + courseId);
		}
		// return coursedao.findById(courseId).orElseThrow(()-> new
		// ResourceNotFoundException("Course not found with id:"+courseId));
		// return coursedao.getOne(courseId);

	}

}
