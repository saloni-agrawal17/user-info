package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.StaleObjectStateException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entitybean.User;
import com.example.demo.entitybean.UserInfo;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	private UserService userService;
		
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public User[] getUsers(){
		
		User []user = null;
		
		try {
		
			String url = "https://dummyjson.com/users";
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(url, String.class);
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(result).get("users");
			user = objectMapper.readValue(jsonNode.toString(), User[].class);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		
		String url = "https://dummyjson.com/users/"+userId;
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(url, User.class);
		return user;
	
	}
	
	@PostMapping("/add-user")
	public UserInfo saveUser(@Valid @RequestBody UserInfo user) {
		
		user.setId(0);
			
		userService.saveUser(user);
		
		return user;
		
	}
	
	@PutMapping("/update-user")
	public UserInfo editUser(@Valid @RequestBody UserInfo user) throws UserNotFoundException{
		
		try {
			userService.saveUser(user); 
		}catch(Exception e) {
			throw new UserNotFoundException("User with id "+user.getId()+" not found");			
		}
		return user;
	}
	
	@DeleteMapping("/remove-user/{userId}")
	public void deleteUser(@PathVariable int userId) throws UserNotFoundException {
		
		try {
			userService.deleteUser(userId);			
		}catch(Exception e) {
			throw new UserNotFoundException("User with id "+userId+" not found");			
		}
		
	}
	
	@GetMapping("/get-department-count")
	public ResponseEntity<Object> getDepartmentCount() {
		
		List<Object> object = userService.getDepartmentCount();
		
		Map<String,Integer> result = new HashMap<String, Integer>();
				
		for(int i = 0;i<object.size();i++) {

			Map map = new HashMap<String,Integer>(); 
			Object []obj = (Object[])object.get(i); 
			 
			String department = obj[0].toString();
			String count = obj[1].toString();
			  
			result.put(department,Integer.parseInt(count)); 
			  
		}
		
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
	
	
	
	
}
