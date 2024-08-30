package com.project.ets.service;

import org.springframework.stereotype.Service;

import com.project.ets.entity.Admin;
import com.project.ets.entity.HR;
import com.project.ets.entity.Student;
import com.project.ets.entity.Trainer;
import com.project.ets.entity.User;
import com.project.ets.enums.UserRole;
import com.project.ets.mapper.UserMapper;
import com.project.ets.repository.UserRepository;
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.StudentRequestDTO;
import com.project.ets.requestdto.TrainerRequestDTO;
import com.project.ets.requestdto.UserRequestDTO;
import com.project.ets.responsedto.UserResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private UserRepository userRepo;
	private UserMapper userMapper;
	
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
	
	public UserResponse updateUser(UserRequestDTO userRequestDTO, String userId,UserRole role) {
		return userRepo.findById(userId)
		.map((user) ->{
				switch(role) {
				case STUDENT:{
					Student student = (Student) user;
					StudentRequestDTO studentRequestDTO = (StudentRequestDTO) userRequestDTO;
					student = userMapper.mapToStudentEntity(studentRequestDTO, student);
					student = userRepo.save(student);
					
					return userMapper.mapToUserResponse(student);
				}
				case TRAINER:{
					Trainer trainer = (Trainer) user;
					TrainerRequestDTO trainerRequestDTO = (TrainerRequestDTO) userRequestDTO;
					trainer = userMapper.mapToTrainerEntity(trainerRequestDTO, trainer);
					trainer = userRepo.save(trainer);
					
					return userMapper.mapToUserResponse(trainer);
				}
				default: return null;
			}
		}).orElseThrow(()-> new IllegalArgumentException("Unexpected value: "+role));
	}
}
