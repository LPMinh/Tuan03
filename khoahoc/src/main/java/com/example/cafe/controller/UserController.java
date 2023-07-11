package com.example.cafe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafe.constants.CafeConstants;
import com.example.cafe.model.ResponseObject;
import com.example.cafe.model.User;
import com.example.cafe.service.CoursesService;
import com.example.cafe.ultis.CafeUntils;

@RestController
@RequestMapping("user")
public class UserController {
//	@Autowired
//	CoursesService userService;
//	@PostMapping("/sign-up")
//	public ResponseEntity<ResponseObject> signUp(@RequestBody(required = true)@Valid User user,BindingResult bindingResult){
//		try {
//			return userService.signUp(user,bindingResult);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return  ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("fail","Something went wrong",""));
//		}
//		
//	}
}
