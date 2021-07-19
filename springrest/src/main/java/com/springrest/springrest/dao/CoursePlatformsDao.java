package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springrest.springrest.entities.CoursePlatforms;

@Repository
public interface CoursePlatformsDao extends JpaRepository<CoursePlatforms,Long> {

}
