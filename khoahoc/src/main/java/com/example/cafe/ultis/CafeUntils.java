package com.example.cafe.ultis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUntils {
	private CafeUntils() {
		
	}
	public static ResponseEntity<String> getResponseEntity (String responseMessage,HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}",httpStatus);
	}
}
