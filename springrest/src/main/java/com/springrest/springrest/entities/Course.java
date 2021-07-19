package com.springrest.springrest.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;



@Entity
@Table(name="course")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})

public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min=2,message="title should have at leat 2 characters")
	private String title;
	
	@NotNull
	@Size(min=10,message="should have a explainatory sentence")
	private String description;
	
	private String sample;
	
	public String getSample() {
		return sample;
	}


	public void setSample(String sample) {
		this.sample = sample;
	}
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="cd_id")
	private CourseDetails courseDetails; 
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="c_id",referencedColumnName="id")
	
	private List<CoursePlatforms> coursePlatforms=new ArrayList<>();
	
	public Course(long id,String title, String description)
	{
		this.id=id;
		this.title=title;
		this.description=description;
	}
	
	
	public Course(String title, String description,CourseDetails courseDetails) {
		super();
		this.title = title;
		this.description = description;
		this.courseDetails=courseDetails;
	}
	public Course() {
		super();
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CourseDetails getCourseDetails() {
		return courseDetails;
	}
	public void setCourseDetails(CourseDetails courseDetails) {
		this.courseDetails = courseDetails;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	public List<CoursePlatforms> getCoursePlatforms() {
		return coursePlatforms;
	}
	public void setCoursePlatforms(List<CoursePlatforms> coursePlatforms) {
		this.coursePlatforms = coursePlatforms;
	}
		
}
