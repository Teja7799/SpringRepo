package com.springrest.springrest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;


public class CourseDto {
	
	private long id;
	
	@NotNull
	@Size(min=2,message="title should have at leat 2 characters")
	private String title;
	
	@NotNull
	@Size(min=10,message="should have a explainatory sentence")
	private String description;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="cd_id")
	private CourseDetailsDto courseDetailsdto; 
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="c_id",referencedColumnName="id")
	
	private List<CoursePlatformsDto> coursePlatformsdto=new ArrayList<>();
	
	public CourseDto(long id,String title, String description)
	{
		this.id=id;
		this.title=title;
		this.description=description;
	}
	
	
	public CourseDto(String title, String description,CourseDetailsDto courseDetailsdto) {
		super();
		this.title = title;
		this.description = description;
		this.courseDetailsdto=courseDetailsdto;
	}
	public CourseDto() {
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
	
	public CourseDetailsDto getCourseDetailsDto() {
		return courseDetailsdto;
	}
	public void setCourseDetails(CourseDetailsDto courseDetailsdto) {
		this.courseDetailsdto = courseDetailsdto;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
	public List<CoursePlatformsDto> getCoursePlatformsDto() {
		return coursePlatformsdto;
	}
	public void setCoursePlatforms(List<CoursePlatformsDto> coursePlatformsdto) {
		this.coursePlatformsdto = coursePlatformsdto;
	}
	
}
