package com.example.task_Manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.task_Manager.model.Todo;
import com.example.task_Manager.service.TmService;

import jakarta.validation.Valid;

@Controller
public class TmController {
	
	 @Autowired
	    private TmService todoItemService;

	    @GetMapping("/create-todo")
	    public String showCreateForm(Todo todoItem) {
	        return "new-todo-item";
	    }

	    @PostMapping("/todo")
	    public String createTodoItem(@Valid Todo todoItem, BindingResult result, Model model) {

	        Todo item = new Todo();
	        item.setDescription(todoItem.getDescription());
	        item.setIsComplete(todoItem.getIsComplete());

	        todoItemService.save(todoItem);
	        return "redirect:/";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
	        Todo todoItem = todoItemService
	                .getById(id)
	                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

	        todoItemService.delete(todoItem);
	        return "redirect:/";
	    }

	    @GetMapping("/edit/{id}")
	    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	        Todo todoItem = todoItemService
	                .getById(id)
	                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

	        model.addAttribute("todo", todoItem);
	        return "edit-todo-item";
	    }

	    @PostMapping("/todo/{id}")
	    public String updateTodoItem(@PathVariable("id") Long id, @Valid Todo todoItem, BindingResult result, Model model) {

	        Todo item = todoItemService
	                .getById(id)
	                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

	        item.setIsComplete(todoItem.getIsComplete());
	        item.setDescription(todoItem.getDescription());

	        todoItemService.save(item);

	        return "redirect:/";
	    }

}
