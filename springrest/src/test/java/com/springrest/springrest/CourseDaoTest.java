package com.springrest.springrest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.springrest.controller.MyController;
import com.springrest.springrest.dao.CourseDao;
import com.springrest.springrest.dao.CourseDetailsDao;
import com.springrest.springrest.entities.Course;
import com.springrest.springrest.entities.CourseDetails;
import com.springrest.springrest.entities.CoursePlatforms;
import com.springrest.springrest.services.CourseService;

@RunWith(SpringRunner.class)
@WebMvcTest(MyController.class)
public class CourseDaoTest {

	@MockBean
	private CourseDao coursedao;

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper objectmapper;

	@MockBean
	private CourseService cs;
	
	@MockBean
	private CourseDetailsDao courseDetailsDao;

	@Test
	public void testGetCourse() throws Exception {
		List<Course> courseList = new ArrayList<>();
		courseList.add(new Course(1L, "iOS course", "iOS developer course"));
//		courseList.add(new Course(2,"Android course","android developer course"));
//		courseList.add(new Course(3,"cisco ntewroking course","computer networking course"));
//		
		when(coursedao.findAll()).thenReturn(courseList);

		String url = "/courses";
		MvcResult mvcresult = mockmvc.perform(get(url)).andExpect(status().isOk()).andDo(print()).andReturn();
		String jsonresponse = mvcresult.getResponse().getContentAsString();
		String expectedjsonresponse = objectmapper.writeValueAsString(courseList);
		assertThat(jsonresponse).isEqualToIgnoringWhitespace(expectedjsonresponse);
	}

	@Test
	public void testPostCourse() throws Exception {
		String uri = "/courses";
		Course course = new Course();
		course.setTitle("Ginger");
		course.setDescription("crazy courses");

		String inputInJson = this.mapToJson(course);

		String URI = "/courses";

		Mockito.when(cs.addCourse(Mockito.any(Course.class))).thenReturn(course);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	public void testCourseDelete() throws Exception {
		Course course = new Course(10000111, "robotics automation", "programming for robotics");

		String inputInJson = this.mapToJson(course);

		String URI = "/courses/10000111";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	public void testCourseUpdate() throws Exception {

		Course course = new Course(10000111, "robotics automation", "programming for robotics");
		String inputInJson = this.mapToJson(course);

		String URI = "/courses/10000111";

		// Mockito.verify(cs, times(1)).updateCourse(course);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetCourseById() throws Exception
	{
		Course course = new Course(10000111, "robotics automation", "programming for robotics");
		String URI = "/courses/10000111";
		
		Mockito.when(cs.getCourse(10000111)).thenReturn(course);
		
		String inputInJson = this.mapToJson(course);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

/** CourseDetails controller test cases **/	
	
	@Test
	public void testGetCourseDetails() throws Exception
	{
		List<CourseDetails> courseList = new ArrayList<>();
		courseList.add(new CourseDetails("10000", "6 months","rambo"));
		when(cs.getCoursesDetails()).thenReturn(courseList);

		String url = "/course/details";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(courseList);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testGetCourseDetailsById() throws Exception
	{
		CourseDetails course = new CourseDetails("1000", "5 months", "dulquer");
		String url = "/courses/details/6";
		
		Mockito.when(cs.getCourseDetails(6)).thenReturn(course);
		
		String inputInJson = this.mapToJson(course);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(url)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testCourseDetailsUpdate() throws Exception {

		CourseDetails course = new CourseDetails("1111", "3 months", "dulquer");
		String inputInJson = this.mapToJson(course);

		String url = "/courses/details/1";

		// Mockito.verify(cs, times(1)).updateCourse(course);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	/** CoursePlatforms controller test cases **/
	
	@Test
	public void testGetCoursePlatforms() throws Exception
	{
		List<CoursePlatforms> courseplatforms = new ArrayList<>();
		courseplatforms.add(new CoursePlatforms( "Udemy","5"));
		when(cs.getCoursePlatforms()).thenReturn(courseplatforms);

		String url = "/course/courseplatforms";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(courseplatforms);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	@Test
	public void testCoursePlatformsUpdate() throws Exception {

		CoursePlatforms course = new CoursePlatforms("liknedin","4");
		String inputInJson = this.mapToJson(course);

		String url = "/course/courseplatforms/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetCoursePlatformsById() throws Exception
	{
		CoursePlatforms courseplatforms = new CoursePlatforms("Courseera", "4.5");
		String url = "/course/courseplatforms/1";
		
		Mockito.when(cs.getCoursePlatformsById(1)).thenReturn(courseplatforms);
		
		String inputInJson = this.mapToJson(courseplatforms);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(url)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
	
		
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
		
}
