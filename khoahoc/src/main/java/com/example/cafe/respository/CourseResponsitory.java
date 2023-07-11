package com.example.cafe.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cafe.model.Course;
import com.example.cafe.model.User;

@Repository
public interface CourseResponsitory extends JpaRepository<Course, Long> {
	@Query(value = "select * from Courses where name=:name",nativeQuery = true)
	public List<Course> findCoureByName(@Param("name") String name);
}