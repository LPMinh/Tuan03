package com.example.cafe.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.cafe.model.Course;
import com.example.cafe.respository.CourseResponsitory;
import com.example.cafe.respository.CourseResponsitoryCus;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class CourseServiceImp implements CoursesService {
	@Autowired
	private CourseResponsitory courseResponsitory;
	@Autowired
	private CourseResponsitoryCus courseResponsitoryCus;
	@Autowired
	private EntityManager entityManager;
	@Override
	public Optional<Course> findCourseByID(Long id) {
		// TODO Auto-generated method stub
		return courseResponsitory.findById(id);
	}

	@Override
	public boolean updateCourse(Course course) {
		// TODO Auto-generated method stub
		
		  // ...
		  Optional<Course> existingCourse =this.findCourseByID(course.getId());
		  if (existingCourse.isPresent()) {
		    Course updatedCourse = entityManager.merge(course);
		    return true;
		  }
		  return false;
	}

	@Override
	public boolean deleteCourseByID(Long id) {
		// TODO Auto-generated method stub
		try {
			courseResponsitory.deleteById(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	@Override
	public Course addCourse(Course course) {
		// TODO Auto-generated method stub
		return courseResponsitory.save(course);
	}

	@Override
	public List<Course> findCoureByName(String name) {
		// TODO Auto-generated method stub
		return courseResponsitory.findCoureByName(name);
	}

	


//	@Autowired
//	private UserRepository userRespository;
//	
//	@Override
//	public User findUserByID(int id) {
//		// TODO Auto-generated method stub
//		return userRespository.getById(id);
//	}
//
//	@Override
//	public boolean updateUser(User user) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean deleteUserByID(int id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean addUser(User user) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	//SignUp 
//	@Override
//	public ResponseEntity<ResponseObject> signUp(@Valid User user,BindingResult bindingResult) {
//		// TODO Auto-generated method stub
//			
////			if(validdateSignUpMap(requestMap)) {
////				User user=  userRespository.findByEmailID(requestMap.get("email"));
////				if(user==null) {
////					userRespository.save(getUserFromMap(requestMap));
////				}else {
////					return CafeUntils.getResponseEntity("Email aldready exist",HttpStatus.BAD_REQUEST);
////					
////				}
////			}else {
////				return CafeUntils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
////			}
//		if (bindingResult.hasErrors()) {
//			 Map<String, String> errorMap=new HashMap<>();
//			 bindingResult.getFieldErrors().forEach(error->{
//				 errorMap.put(error.getField(), error.getDefaultMessage());
//			 });
//			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("fail","invalid error", errorMap));
//        }else {
//        	
////        	User unique=userRespository.findByEmailID(user.getEmail());
////        	if(unique==null) {
////        		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject("successful","Sign up thành công",userRespository.save(user)));
////        		
////        	}else {
////        		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("fail","Email đã có ",""));
////        	}
//        }
			
		
		
	}
//
//	@Override
//	public boolean validdateSignUpMap(Map<String, String> requestMap) {
//		if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password")) {
//			return true;
//		}
//		return false;
//	}
//	private User getUserFromMap(Map<String, String> map) {
//		User user=new User();
//		String email=map.get("email");
//		String contactNumber=map.get("contactNumber");
//		String password=map.get("password");
//		user.setContactNumber(contactNumber);
//		user.setEmail(email);
//		user.setPassword(password);
//		user.setName(map.get("name"));
//		user.setRole("user");
//		user.setStatus("false");
//		return user;
//		
//	}

	
//}
