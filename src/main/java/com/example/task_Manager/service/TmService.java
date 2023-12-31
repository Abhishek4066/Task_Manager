package com.example.task_Manager.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.task_Manager.model.Todo;
import com.example.task_Manager.repository.TmRepository;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class TmService {

	 @Autowired
	    private TmRepository todoItemRepository;

	    public Optional<Todo> getById(Long id) {
	        return todoItemRepository.findById(id);
	    }

	    public Iterable<Todo> getAll() {
	        return todoItemRepository.findAll();
	    }

	    public Todo save(Todo todoItem) {
	        if (todoItem.getId() == null) {
	            todoItem.setCreatedAt(Instant.now());
	        }
	        todoItem.setUpdatedAt(Instant.now());
	        return todoItemRepository.save(todoItem);
	    }

	    public void delete(Todo todoItem) {
	        todoItemRepository.delete(todoItem);
	    }
}
