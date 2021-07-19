package com.springrest.springrest.services;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.CourseDetailsDao;
import com.springrest.springrest.dao.CoursePlatformsDao;
import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.CourseDetails;
import com.springrest.springrest.entities.CoursePlatforms;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

	@InjectMocks
	private CourseServiceImpl courseimpl ;
	
	@Mock
	private CourseDao courseDao;
	
	@Mock
	private CourseDetailsDao courseDetailsDao;
	
	@Mock
	private CoursePlatformsDao coursePlatformsDao;
	
	@Test
	void testGetCourses() {
		when(courseDao.findAll()).thenReturn(Stream.of(new Course(1,"android", "android course"),new Course(2,"android", "android course")).collect(Collectors.toList()));;
		assertEquals(2, courseimpl.getCourses().size());
	}

	@Test
	void testGetCourse() {
		Course cour=new Course(6,"cryptograhy","dfjfjfj");
		when(this.courseDao.findById(cour.getId())).thenReturn(Optional.of(cour));
		Course course = courseimpl.getCourse(6);
		assertEquals(course.getTitle(), cour.getTitle());
	}

	@Test
	void testAddCourse() {
		
		Course course = new Course(1,"Android", "android course");

		when(courseDao.save(course)).thenReturn(course);

		Course course1 = courseimpl.addCourse(course);
		assertEquals(course1.getTitle(),course.getTitle());
		assertEquals(course1.getDescription(),course.getDescription());

	}

	@Test
	void testUpdateCourse() {
		Course course=new Course(6,"cryptograhy","dfjfjfj");
		Mockito.when(courseDao.findById(6L)).thenReturn(Optional.of(course));
		courseimpl.updateCourse(new Course(1,"apple","apple course"),6);
		assertEquals(course.getTitle(),"apple");
		
	}

	@Test
	void testDeleteCourse() {
		Course course = new Course();
		course.setId(1);
		when(courseDao.findById(course.getId())).thenReturn(Optional.of(course));
		courseimpl.deleteCourse(1);
		verify(courseDao).delete(course);
	}
	
	/** Sub-resource course Details test cases **/
	
	@Test
	void testGetCourseDetails() {
		CourseDetails coursedetails=new CourseDetails("100","3 mon","rambo");
		when(courseDetailsDao.findAll()).thenReturn(Stream.of(coursedetails).collect(Collectors.toList()));;
		assertEquals(1, courseimpl.getCoursesDetails().size());
	}
	
	@Test
	void testGetCourseDetailsById() {
		CourseDetails coursedetails=new CourseDetails("6000","5 months","hrithik");
		when(this.courseDetailsDao.findById(coursedetails.getId())).thenReturn(Optional.of(coursedetails));
		CourseDetails coursedetails1 = courseimpl.getCourseDetails(coursedetails.getId());
		assertEquals(coursedetails1.getCoursePrice(), coursedetails.getCoursePrice());
	}
	
	@Test
	void testUpdateCourseDetails() {
		CourseDetails coursedetails=new CourseDetails("200","3 months","vijay");
		Mockito.when(courseDetailsDao.findById(6L)).thenReturn(Optional.of(coursedetails));
		courseimpl.updateCourseDetails(new CourseDetails("200","5 months","deverkonda"),6);
		assertEquals(coursedetails.getCourseDuration(),"5 months");
		assertNotEquals(coursedetails.getCourseInstructor(),"vijay");
		
	}
	
	/** Sub-resource course platform test cases **/
	
	@Test
	void testGetCoursePlatforms() {
		CoursePlatforms courseplatforms=new CoursePlatforms("Udemy","4.5");
		when(coursePlatformsDao.findAll()).thenReturn(Stream.of(courseplatforms).collect(Collectors.toList()));;
		assertEquals(1, courseimpl.getCoursePlatforms().size());
	}
	
	@Test
	void testGetCoursePlatformsById() {
		CoursePlatforms courseplatforms=new CoursePlatforms("LinkedIn","5");
		when(this.coursePlatformsDao.findById(courseplatforms.getCoursePlatformId())).thenReturn(Optional.of(courseplatforms));
		CoursePlatforms courseplatforms1 = courseimpl.getCoursePlatformsById(0);
		assertEquals(courseplatforms1.getCoursePlatformName(),"LinkedIn");
	}
	
//	@Test
//	void testUpdateCoursePlatforms() {
//		List<CoursePlatforms> courseplatforms=new ArrayList<>(Arrays.asList(new CoursePlatforms("uuu","4")));
//		Course course= new Course();
//		course.setId(1);
//		course.setCoursePlatforms(courseplatforms);
//		Mockito.when(courseDao.findById(1L).thenReturn(Optional.of(course)));
//		courseimpl.updateCoursePlatformsByCourseId(new CoursePlatforms("edureka","5"),courseplatforms.getCoursePlatformId());
//		assertEquals(courseplatforms.getCoursePlatformName(),"Course era");
//		}
	
	
}
