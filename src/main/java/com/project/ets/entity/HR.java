package com.project.ets.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "hrs")
@EntityListeners(AuditingEntityListener.class)
public class HR extends User{

}
