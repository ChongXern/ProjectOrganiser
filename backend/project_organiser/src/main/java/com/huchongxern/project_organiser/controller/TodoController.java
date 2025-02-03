package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Todo;
import com.huchongxern.project_organiser.service.TodoService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable ObjectId todoId) {
        Todo foundTodo = todoService.getTodoById(todoId);
        return new ResponseEntity<>(foundTodo, HttpStatus.OK);
    }

    @PostMapping("/createTodo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
}
