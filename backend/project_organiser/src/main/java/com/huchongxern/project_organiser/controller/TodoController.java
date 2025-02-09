package com.huchongxern.project_organiser.controller;

import com.huchongxern.project_organiser.model.Todo;
import com.huchongxern.project_organiser.service.TodoService;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/status")
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

    @PatchMapping("/updateTodo/desc/{todoId}")
    public ResponseEntity<Todo> updateTodoDesc(@PathVariable ObjectId todoId, @RequestParam String desc) {
        return new ResponseEntity<>(todoService.updateTodoDesc(todoId, desc), HttpStatus.OK);
    }

    @PatchMapping("/updateTodo/createdDate/{todoId}")
    public ResponseEntity<Todo> updateTodoCreatedDate(@PathVariable ObjectId todoId, @RequestParam Date createdDate){
        return new ResponseEntity<>(todoService.updateTodoCreateDate(todoId, createdDate), HttpStatus.OK);
    }

    @PatchMapping("updateTodo/startTime/{todoId}")
    public ResponseEntity<Todo> updateTodoStartTime(@PathVariable ObjectId todoId, @RequestParam Date startTime) {
        return new ResponseEntity<>(todoService.updateTodoStartTime(todoId, startTime), HttpStatus.OK);
    }

    @PatchMapping("updateTodo/deadline/{todoId}")
    public ResponseEntity<Todo> updateTodoDeadline(@PathVariable ObjectId todoId, @RequestParam Date deadline) {
        return new ResponseEntity<>(todoService.updateTodoDeadline(todoId, deadline), HttpStatus.OK);
    }

    @PatchMapping("updateTodo/isDone/{todoId}")
    public ResponseEntity<Todo> updateTodoIsDone(@PathVariable ObjectId todoId, @RequestParam boolean isDone) {
        return new ResponseEntity<>(todoService.updateTodoIsDone(todoId, isDone), HttpStatus.OK);
    }

    @PatchMapping("updateTodo/priority/{todoId}")
    public ResponseEntity<Todo> updateTodoPriority(@PathVariable ObjectId todoId, @RequestParam int priority){
        return new ResponseEntity<>(todoService.updateTodoPriority(todoId, priority), HttpStatus.OK);
    }

    @PatchMapping("/updateTodo/any/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable ObjectId todoId,
                                           @RequestBody Map<String, Object> updates) {
        try {
            return new ResponseEntity<>(todoService.patchTodo(todoId, updates), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // invalid field
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
