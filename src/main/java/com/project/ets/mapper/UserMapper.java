package com.project.ets.mapper;

import org.springframework.stereotype.Component;

import com.project.ets.entity.Student;
import com.project.ets.entity.Trainer;
import com.project.ets.entity.User;
import com.project.ets.requestdto.RegisterationRequestDTO;
import com.project.ets.requestdto.StudentRequestDTO;
import com.project.ets.requestdto.TrainerRequestDTO;
import com.project.ets.responsedto.StudentResponseDTO;
import com.project.ets.responsedto.TrainerResponseDTO;
import com.project.ets.responsedto.UserResponse;

@Component
public class UserMapper {
	
	public User mapToUserEntity(RegisterationRequestDTO registerationRequestDTO,User user) {
		user.setUserName(registerationRequestDTO.getUserName());
		user.setEmail(registerationRequestDTO.getEmail());
		user.setPassword(registerationRequestDTO.getPassword());
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		UserResponse response  = new UserResponse();
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setEmail(user.getEmail());
		return response;
	}
	
	public Student mapToStudentEntity(StudentRequestDTO studentRequestDTO, Student student) {
		student.setUserName(studentRequestDTO.getUsername());
		student.setEmail(studentRequestDTO.getEmail());
		student.setDegree(studentRequestDTO.getDegree());
		student.setStream(studentRequestDTO.getStream());
		student.setYearOfPassout(studentRequestDTO.getYop());
		student.setDegreePercentage(studentRequestDTO.getDegreePercentage());
		student.setTenthPercentage(studentRequestDTO.getTenthPercentage());
		student.setTwelthPercentage(studentRequestDTO.getTwelvethPercentage());
		return student;
	}
	
	public StudentResponseDTO mapToStudentResponse(Student student) {
		StudentResponseDTO response = new StudentResponseDTO();
		response.setUserId(student.getUserId());
		response.setUserName(student.getUserName());
		response.setEmail(student.getEmail());
		response.setDegree(student.getDegree());
		response.setStream(student.getStream());
		response.setYop(student.getYearOfPassout());
		response.setDegreePercentage(student.getDegreePercentage());
		response.setTenthPercentage(student.getTenthPercentage());
		response.setTwelthPercentage(student.getTwelthPercentage());
		return response;
	}
	
	public Trainer mapToTrainerEntity(TrainerRequestDTO trainerRequest,Trainer trainer) {
		trainer.setUserName(trainerRequest.getUsername());
		trainer.setEmail(trainerRequest.getEmail());
		trainer.setSubjects(trainerRequest.getSubjects());
		return trainer;
	}
	
	public TrainerResponseDTO mapToTrainerResponse(Trainer trainer) {
		TrainerResponseDTO response = new TrainerResponseDTO();
		response.setUserId(trainer.getUserId());
		response.setUserName(trainer.getUserName());
		response.setEmail(trainer.getEmail());
		response.setSubjects(trainer.getSubjects()); 
		return response;
	}
}
