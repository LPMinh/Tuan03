package com.example.cafe.respository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.example.cafe.model.Course;
@Repository
public class CourseResponsitoryCus  {
	 @PersistenceContext
	    private EntityManager entityManager;

	 
	   
	    public Course createCourse(Course course) {
	        Course mergedCourse = entityManager.merge(course);
	        entityManager.flush(); // Đảm bảo thay đổi được đồng bộ với cơ sở dữ liệu
	        return mergedCourse;
	    }

		
}
