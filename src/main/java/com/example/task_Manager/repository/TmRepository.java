package com.example.task_Manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task_Manager.model.Todo;

public interface TmRepository extends JpaRepository<Todo, Long>{
	
	

}
