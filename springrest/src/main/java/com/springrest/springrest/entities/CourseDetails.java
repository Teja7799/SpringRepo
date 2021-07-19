package com.springrest.springrest.entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_Details")
public class CourseDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String coursePrice;
	private String courseDuration;
	private String courseInstructor;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="courseDetails")
	private Course course;
	
	public CourseDetails() {
				
	}
	public CourseDetails(String coursePrice, String courseDuration, String courseInstructor) {
		super();
		this.coursePrice = coursePrice;
		this.courseDuration = courseDuration;
		//this.coursePlatform = coursePlatform;
		this.courseInstructor=courseInstructor;
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	@Override
	public String toString() {
		return "CourseDetails [coursePrice=" + coursePrice + ", courseDuration=" + courseDuration + ", courseInstructor=" + courseInstructor + "]";
	}
	
}
