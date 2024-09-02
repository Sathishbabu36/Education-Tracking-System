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
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.StudentRequestDTO;
import com.project.ets.requestdto.TrainerRequestDTO;
import com.project.ets.responsedto.RatingResponse;
import com.project.ets.responsedto.StudentResponseDTO;
import com.project.ets.responsedto.UserResponse;
import com.project.ets.service.UserService;
import com.project.ets.util.AppResponseBuilder;
import com.project.ets.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private AppResponseBuilder responseBuilder;



	@Operation(description = "The API endpoint is used to register the admins",
			responses = {
					@ApiResponse(responseCode = "201", description = "Admin created"),
					@ApiResponse(responseCode = "500", description = "Internal Sever Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})
	@PostMapping("/admins/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveAdmin(
			@RequestBody @Valid RegisterationRequestDTO registerationRequestDTO) {
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.ADMIN);
		return responseBuilder.success(HttpStatus.CREATED, "admin successfully created", response);
	}

	@Operation(description = "The API endpoint is used to register the Hrs",
			responses = {
					@ApiResponse(responseCode = "201",description = "HR created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})
	@PostMapping("/hrs/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveHR(
			@RequestBody @Valid RegisterationRequestDTO registerationRequestDTO) {
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.HR);
		return responseBuilder.success(HttpStatus.CREATED, "HR Created", response);
	}

	@Operation(description = "The API endpoint is used to register the trainers",
			responses = {
					@ApiResponse(responseCode = "201",description = "Trainer created"),
					@ApiResponse(responseCode = "500",description = "Internal server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})
	@PostMapping("/trainers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveTrainer(
			@RequestBody @Valid RegisterationRequestDTO registerationRequestDTO) {
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.TRAINER);
		return responseBuilder.success(HttpStatus.CREATED, "Trainer created", response);
	}

	@Operation(description = "The API endpoint is used to register the students",
			responses = {
					@ApiResponse(responseCode = "201", description = "Student created"),
					@ApiResponse(responseCode = "501", description = "Internal server error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})
	@PostMapping("/students/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveStudent(
			@RequestBody @Valid RegisterationRequestDTO registerationRequestDTO) {
		UserResponse response = userService.saveUser(registerationRequestDTO, UserRole.STUDENT);
		return responseBuilder.success(HttpStatus.CREATED, "student created", response);
	}

	@Operation(description = "The API endpoint is used to update the student",responses = {
			@ApiResponse(responseCode = "200",description = "Student updated"),
			@ApiResponse(responseCode = "501",description = "Internal server error",
			content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@PutMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudent(@PathVariable String userId,
			@RequestBody StudentRequestDTO studentRequest) {
		StudentResponseDTO studentResponse = userService.updateStudent(studentRequest, userId);
		return responseBuilder.success(HttpStatus.OK, "student updated successfully", studentResponse);
	}

	@Operation(description = "The API endpoint is used to update the student",responses = {
			@ApiResponse(responseCode = "200",description = "student stack updated"),
			@ApiResponse(responseCode = "501",description = "Internal server error",
			content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})

	})
	@PatchMapping("/students/{userId}")
	public ResponseEntity<ResponseStructure<StudentResponseDTO>> updateStudentStack(@RequestParam Stack stack,
			@PathVariable String userId) {
		StudentResponseDTO response = userService.updateStudent(stack, userId);
		return responseBuilder.success(HttpStatus.OK, "student stack is updated", response);
	}

	@Operation(description = "The API endpoint is used to update trainer",responses = {
			@ApiResponse(responseCode = "200",description = "trainer is updated"),
			@ApiResponse(responseCode = "501",description = "Internal server error",
			content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))	
			})
	})
	@PutMapping("/trainers/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateTrainer(@RequestBody TrainerRequestDTO trainerRequest,
			@PathVariable String userId) {
		UserResponse response = userService.updateTrainer(trainerRequest, userId);
		return responseBuilder.success(HttpStatus.OK, "Trainer updated", response);
	}

	@Operation(description = "The API endpoint is used to view ratings",responses = {
			@ApiResponse(responseCode = "302",description = "raings found"),
			@ApiResponse(responseCode = "500",description = "Internal server error",
			content = {
					@Content(schema = @Schema(anyOf = RuntimeException.class))
			})
	})
	@GetMapping("/students/{studentId}/ratings")
	public ResponseEntity<ResponseStructure<List<RatingResponse>>> viewRating(@PathVariable String studentId) {
		List<RatingResponse> responses = userService.viewRating(studentId);
		return responseBuilder.success(HttpStatus.FOUND, "found the ratings of the student", responses);
	}
}
