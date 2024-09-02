package com.project.ets.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ets.entity.Admin;
import com.project.ets.entity.HR;
import com.project.ets.entity.Rating;
import com.project.ets.entity.Student;
import com.project.ets.entity.Trainer;
import com.project.ets.entity.User;
import com.project.ets.enums.Stack;
import com.project.ets.enums.UserRole;
import com.project.ets.exception.UserNotFoundByIdException;
import com.project.ets.mapper.RatingMapper;
import com.project.ets.mapper.UserMapper;
import com.project.ets.repository.RatingRepository;
import com.project.ets.repository.UserRepository;
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.StudentRequestDTO;
import com.project.ets.requestdto.TrainerRequestDTO;
import com.project.ets.responsedto.RatingResponse;
import com.project.ets.responsedto.StudentResponseDTO;
import com.project.ets.responsedto.UserResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepo;
	private UserMapper userMapper;
	private RatingRepository ratingRepository;
	private RatingMapper ratingMapper;
	
	public UserResponse saveUser(RegisterationRequestDTO registerationRequestDTO,UserRole role) {
		
		User user = null;
		
		switch (role) {
		case ADMIN -> user = new Admin();
		case HR -> user = new HR();
		case TRAINER -> user = new Trainer();
		case STUDENT -> user = new Student();
		
		default -> throw new IllegalArgumentException("Unexpected value: "+role);
		}
		
		if(user != null) {
			user = userMapper.mapToUserEntity(registerationRequestDTO, user);
			user.setRole(role);
			user = userRepo.save(user);
		}
		
		return userMapper.mapToUserResponse(user);
	}
	
	public UserResponse updateTrainer(TrainerRequestDTO trainerRequest,String userId) {
		return userRepo.findById(userId).map((user)->{
			user = userMapper.mapToTrainerEntity(trainerRequest, (Trainer) user);
			user = userRepo.save(user);
			return userMapper.mapToUserResponse(user);
		}).orElseThrow(()-> new UserNotFoundByIdException("failed to update the trainer"));
	}
	
	public StudentResponseDTO updateStudent(StudentRequestDTO studentRequest,String userId) {
		return userRepo.findById(userId).map((user)->{
			user=userMapper.mapToStudentEntity(studentRequest, (Student)user);
			user=userRepo.save(user);
			return userMapper.mapToStudentResponse((Student) user);
		}).orElseThrow(()-> new UserNotFoundByIdException("failed to update student"));
	}
	
	public StudentResponseDTO updateStudent(Stack stack, String userId) {
		return userRepo.findById(userId)
		.map(user ->{
			Student student = (Student) user;
			stack.getSubjects().forEach(subject -> {
				Rating rating = new Rating();
				rating.setSubject(subject);
				rating.setStudent(student);
				ratingRepository.save(rating);
			});
			student.setStack(stack);
			user = userRepo.save(student);
			return userMapper.mapToStudentResponse(student);
		}).orElseThrow(()-> new UserNotFoundByIdException("failed to update stack to the student"));
	}
	
	public List<RatingResponse> viewRating(String userId){
		return userRepo.findById(userId)
			.map(user ->{
			Student student = (Student) user;
			return student.getRatings()
					.stream()
					.map(rating -> ratingMapper.mapToRatingReponse(rating))
					.toList();
		}).orElseThrow(()-> new UserNotFoundByIdException("student is not found by the given id"));
	}
}
