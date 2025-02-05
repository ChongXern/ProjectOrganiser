package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Todo;
import com.huchongxern.project_organiser.service.TodoService;
import org.apache.coyote.Response;
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

    @GetMapping("SortedPriority")
    public ResponseEntity<List<Todo>> getAllTodosSortedByPriority() {
        List<Todo> sortedTodos = todoService.getAllTodosSortedByPriority();
        return new ResponseEntity<>(sortedTodos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable ObjectId todoId) {
        Todo foundTodo = todoService.getTodoById(todoId);
        return new ResponseEntity<>(foundTodo, HttpStatus.OK);
    }

    @GetMapping("/completed?={isComplete}")
    public ResponseEntity<List<Todo>> getTodosByCompleteStatus(@PathVariable boolean isComplete) {
        List<Todo> todoList;
        if (isComplete) {
            todoList = todoService.getCompletedTodos();
        } else {
            todoList = todoService.getPendingTodos();
        }
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @PostMapping("/createTodo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/updateTodo/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable ObjectId todoId, @RequestBody Todo newTodo) {
        return new ResponseEntity<>(todoService.updateTodo(todoId, newTodo), HttpStatus.OK);
    }

    @DeleteMapping("/deleteTodo/{todoId}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable ObjectId todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }
}
