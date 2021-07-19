package com.springrest.springrest.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.springrest.springrest.annotation.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Course_Platforms")
public class CoursePlatforms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long coursePlatformId;
	private String coursePlatformName;

	@Rating(message = "Invalid rating, give between 1-5")
	private String coursePlatformRating;

	public CoursePlatforms() {
		super();

	}

	public CoursePlatforms(String coursePlatformName, String coursePlatformRating) {
		super();
		this.coursePlatformName = coursePlatformName;
		this.coursePlatformRating = coursePlatformRating;
	}

	public long getCoursePlatformId() {
		return coursePlatformId;
	}

	public void setCoursePlatformId(long coursePlatformId) {
		this.coursePlatformId = coursePlatformId;
	}

	public String getCoursePlatformName() {
		return coursePlatformName;
	}

	public void setCoursePlatformName(String coursePlatformName) {
		this.coursePlatformName = coursePlatformName;
	}

	public String getCoursePlatformRating() {
		return coursePlatformRating;
	}

	public void setCoursePlatformRating(String coursePlatformRating) {
		this.coursePlatformRating = coursePlatformRating;
	}

	@Override
	public String toString() {
		return "CoursePlatforms [coursePlatformId=" + coursePlatformId + ", coursePlatformName=" + coursePlatformName
				+ ", coursePlatformRating=" + coursePlatformRating + "]";
	}

}
