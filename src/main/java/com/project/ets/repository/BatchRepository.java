package com.project.ets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ets.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch,String>{

}
