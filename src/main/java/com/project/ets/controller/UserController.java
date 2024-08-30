package com.project.ets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ets.enums.UserRole;
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.UserRequestDTO;
import com.project.ets.responsedto.UserResponse;
import com.project.ets.service.UserService;
import com.project.ets.util.AppResponseBuilder;
import com.project.ets.util.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	private AppResponseBuilder responseBuilder;
	
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO){
		UserResponse response = userService.saveUser(registerationRequestDTO,UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.CREATED, "admin successfully created", response);
	}
	
	@PostMapping("/hrs")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO){
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created", response);
	}
	
	@PostMapping("/trainers")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO){
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created", response);
	}
	
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO){
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "student created", response);
	}
	
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateStudent(@PathVariable String userId,@RequestBody@Valid UserRequestDTO userRequestDTO){
		UserResponse response = userService.updateUser(userRequestDTO,userId, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.OK,"student updated successfully", response);
	}
	
}
