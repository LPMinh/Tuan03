package com.example.cafe.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.example.cafe.model.Course;
import com.example.cafe.model.ResponseObject;
import com.example.cafe.model.User;
@Service
public interface CoursesService {
	public Optional<Course> findCourseByID(Long id);
	public boolean updateCourse(Course user);
	public boolean deleteCourseByID(Long id);
	public Course addCourse(Course course);
	public List<Course> findCoureByName(String name);
	
}
