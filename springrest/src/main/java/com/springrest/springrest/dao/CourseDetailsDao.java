package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entities.CourseDetails;

@Repository
public interface CourseDetailsDao extends JpaRepository<CourseDetails, Long> {

}
