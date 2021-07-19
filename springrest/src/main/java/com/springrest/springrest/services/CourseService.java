package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.dto.CourseDetailsDto;
import com.springrest.springrest.dto.CourseDto;
import com.springrest.springrest.dto.CoursePlatformsDto;
import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.CourseDetails;
import com.springrest.springrest.entities.CoursePlatforms;

public interface CourseService {
	
	public List<CourseDto> getCourses();
	
//	public CourseDto getCourse(long courseId);
	
	public Course getCourse(long courseId);
	
	public CourseDto addCourse(CourseDto coursedto);

	public CourseDto updateCourse(CourseDto coursedto,long courseId);
	
	public void deleteCourse(long parselong);
	
	public CourseDetails updateCourseDetails(CourseDetails courseDetails,long coursedetailsId);
	
	public CourseDetailsDto getCourseDetails(long coursedetailsId);
	
	public List<CourseDetailsDto> getCoursesDetails();
	
	public List<CoursePlatformsDto> getCoursePlatforms();
	
	public CoursePlatformsDto getCoursePlatformsById(long courseId);
	
	public CoursePlatforms updateCoursePlatforms(CoursePlatforms coursePlatforms,long courseplatformId);
	
	public List<CoursePlatformsDto> getAllCoursePlatformsByDetailsId(long courseId);
	
	public List<CoursePlatforms> getCoursePlatformParam(long courseId,long platformId);
	
	public List<CoursePlatforms> updateCoursePlatformsByCourseId(List<CoursePlatforms> course, long courseId);
}
