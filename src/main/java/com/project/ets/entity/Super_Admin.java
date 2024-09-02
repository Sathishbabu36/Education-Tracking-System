package com.project.ets.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "super_admins")
@EntityListeners(AuditingEntityListener.class)
public class Super_Admin extends User{
	
}
