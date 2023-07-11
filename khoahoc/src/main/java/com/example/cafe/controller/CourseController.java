package com.example.cafe.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.cafe.dto.CourseDTO;
import com.example.cafe.model.Course;
import com.example.cafe.model.ResponseObject;
import com.example.cafe.model.User;
import com.example.cafe.respository.CourseResponsitory;
import com.example.cafe.respository.UserRepository;
import com.example.cafe.service.CoursesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CoursesService coursesService;
	private Jedis jedis = new Jedis();
	private ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private CourseResponsitory courseResponsitory;
	@Autowired
	private UserRepository userRepository;
	private Gson gson=new Gson();
	@PostMapping("/create")
	public ResponseEntity<ResponseObject> createCourse(@RequestBody CourseDTO courseDTO) {
		
		try {
			Course course=new Course();
			User user = userRepository.findById(courseDTO.getIdUser()).get();
			course.setDescription(courseDTO.getDescription());
			course.setName(courseDTO.getName());
			course.setPrice(courseDTO.getPrice());
			course.setImage(courseDTO.getImage());
			course.setUser(user);
			if (coursesService.addCourse(course) != null) {
				String json = objectMapper.writeValueAsString(user);
				jedis.set(course.getId().toString(), json);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "them thanh cong", course));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("fail", "them6 that61 bai", ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject("fail", "Something went wrong", ""));
		}

	}

	@PostMapping("/update")
	public ResponseEntity<ResponseObject> updateCourse(@RequestBody Course course) {
		try {
			if (coursesService.updateCourse(course)) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "update thanh cong", course));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("fail", "update that bai", ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject("fail", "Something went wrong", ""));
		}

	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseObject> deleteCourse(@PathVariable("id") Long id) {
		try {
			if (coursesService.deleteCourseByID(id)) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "xoa thanh cong", ""));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("fail", "xoa  that bai", ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject("fail", "Something went wrong", ""));
		}

	}

	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseObject> findCourseByID(@PathVariable Long id) {
		try {
			if (jedis.get(id.toString()) != null) {
				String json = jedis.get(id.toString());
				Course course = objectMapper.readValue(json,Course.class);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "tim thay", course));
			} else {
				if (coursesService.findCourseByID(id) != null) {
					return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("ok", "tim thay", coursesService.findCourseByID(id)));
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new ResponseObject("fail", "Khong tim thay", ""));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject("fail", "Something went wrong", ""));
		}

	}

	@GetMapping(value = "/findbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> findCourseByName(@PathVariable String name) {
		try {
			if (coursesService.findCoureByName(name) != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "tim thay", coursesService.findCoureByName(name)));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("fail", "khong tim thay", ""));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject("fail", "Something went wrong", ""));
		}

	}

	@GetMapping("/get-from-jedis/{id}")
	public ResponseEntity<ResponseObject> getCourseFromJedis(@PathVariable String id) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String json = jedis.get(id);
			Course course = objectMapper.readValue(json, Course.class);
			if (course != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Tim thay", course));

			}
			return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
					.body(new ResponseObject("fail", "khong tim thay", ""));

		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
					.body(new ResponseObject("fail", "khong tim thay", ""));

		}

	}

	@GetMapping("/findByNameUsingExternalApi/{name}")
	public ResponseEntity<String> getDataFromExternalApi(@PathVariable String name) {
//		try {
//			// Tạo đối tượng HttpClient
//			org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create().build();
//
//			// Tạo yêu cầu GET tới API khác
//			HttpGet request = new HttpGet("http://localhost:8080/course/findbyname/" + name + "");
//
//			// Thực hiện yêu cầu và nhận phản hồi
//			HttpResponse response = httpClient.execute(request);
//
//			// Lấy phần thân (body) của phản hồi
//			HttpEntity entity = response.getEntity();
//			String responseBody = EntityUtils.toString(entity);
//
//			// Kiểm tra mã trạng thái của phản hồi
//			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
//				return ResponseEntity.ok(responseBody);
//			} else {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//						.body("Failed to fetch data from external API");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Failed to fetch data from external API");
//		}
		RestTemplate restTemplate = new RestTemplate();

		String apiUrl = "http://localhost:8080/course/findbyname/" + name + "";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
		String responseBody = responseEntity.getBody();
		return ResponseEntity.ok().body(responseBody);
		
	}
}
