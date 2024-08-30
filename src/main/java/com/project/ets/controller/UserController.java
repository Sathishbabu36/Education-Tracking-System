package com.project.ets.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ets.enums.Stack;
import com.project.ets.enums.UserRole;
import com.project.ets.mapper.RatingMapper;
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.StudentRequestDTO;
import com.project.ets.requestdto.TrainerRequestDTO;
import com.project.ets.responsedto.RatingResponse;
import com.project.ets.responsedto.StudentResponseDTO;
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
	
	@PostMapping("/users/admins/{role}")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO,@PathVariable UserRole role){
		UserResponse response = userService.saveUser(registerationRequestDTO,role);
		return responseBuilder.success(HttpStatus.CREATED, "admin successfully created", response);
	}
	
	@PostMapping("/users/hrs/{role}")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO,@PathVariable UserRole role){
		UserResponse response = userService.saveUser(registerationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created", response);
	}
	
	@PostMapping("/users/trainers/{role}")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO,@PathVariable UserRole role){
		UserResponse response = userService.saveUser(registerationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created", response);
	}
	
	@PostMapping("/users/students/{role}")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(@RequestBody@Valid RegisterationRequestDTO registerationRequestDTO,@PathVariable UserRole role){
		UserResponse response = userService.saveUser(registerationRequestDTO, role);
		return responseBuilder.success(HttpStatus.CREATED, "student created", response);
	}
	
	@PutMapping("/users/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(@PathVariable String userId,@RequestBody StudentRequestDTO studentRequest){
		StudentResponseDTO studentResponse =  userService.updateStudent(studentRequest,userId);
		return responseBuilder.success(HttpStatus.OK,"student updated successfully", studentResponse);
	}
	
	@PatchMapping("/users/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack, @PathVariable String userId){
		StudentResponseDTO response = userService.updateStudent(stack, userId);
		return responseBuilder.success(HttpStatus.OK, "student stack is updated", response);
	}
	
	@PutMapping("/users/trainers/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateTrainer(@RequestBody TrainerRequestDTO trainerRequest,@PathVariable String userId){
		UserResponse response = userService.updateTrainer(trainerRequest, userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer updated",response);
	}
	
	@GetMapping("users/students/ratings/{userId}")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>>viewRating (@PathVariable String userId){
		List<RatingResponse> responses = userService.viewRating(userId);
		return responseBuilder.success(HttpStatus.FOUND, "found the ratings of the student", responses);
	}
}
