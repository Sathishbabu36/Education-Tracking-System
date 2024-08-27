package com.project.ets.config;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.project.ets.entity.Super_Admin;
import com.project.ets.entity.User;
import com.project.ets.enums.UserRole;
import com.project.ets.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class SuperAdminRegisterationEvent {
	
	private UserRepository userRepo;
	
	@Value("${super_admin.email}")
	private String email;
	
	public SuperAdminRegisterationEvent(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}



	@EventListener(ApplicationReadyEvent.class)
	public void registerSuperAdmin() {
		log.info("checking if super_admin is present");
		List<User> list= userRepo.findByRole(UserRole.SUPER_ADMIN);
		if(list.isEmpty()) {
			log.info("super admin is not present creating one");
			User user = new Super_Admin();
			user.setEmail(email);
			user.setPassword(UUID.randomUUID().toString());
			user.setRole(UserRole.SUPER_ADMIN);
			user.setUserName("admin");
			userRepo.save(user);
		}
		else
			log.info("super admin present with email: "+list.get(0).getEmail());
	}
}
