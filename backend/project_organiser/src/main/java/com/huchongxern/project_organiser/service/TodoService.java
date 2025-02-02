package com.huchongxern.project_organiser.service;

import com.huchongxern.project_organiser.model.Project;
import com.huchongxern.project_organiser.model.Todo;
import com.huchongxern.project_organiser.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> getTodoById(ObjectId id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public List<Todo> getAllTodosSortedByPriority() {
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    public List<Todo> getCompletedTodos() {
        return todoRepository.findTodoByIsDone(true).orElse(Collections.emptyList());
    }

    public List<Todo> getPendingTodos() {
        return todoRepository.findTodoByIsDone(false).orElse(Collections.emptyList());
    }

    public Todo updateTodo(ObjectId id, Todo todo) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo item not found with ID: " + id));

        existingTodo.setDesc(todo.getDesc());
        existingTodo.setCreatedDate(todo.getCreatedDate());
        existingTodo.setStartTime(todo.getStartTime());
        existingTodo.setDeadline(todo.getDeadline());
        existingTodo.setDone(todo.isDone());
        existingTodo.setPriority(todo.getPriority());

        return todoRepository.save(existingTodo);
    }

    public void deleteTodo(ObjectId id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo item not found with ID: " + id);
        }
        todoRepository.deleteById(id);
    }

    public Todo patchTodo(ObjectId id, Map<String, Object> updates) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo item not found with ID: " + id));

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            switch (field) {
                case "desc":
                    existingTodo.setDesc((String) value);
                    break;
                case "created_date":
                    existingTodo.setCreatedDate((Date) value);
                    break;
                case "start_time":
                    existingTodo.setStartTime((Date)value);
                    break;
                case "deadline":
                    existingTodo.setDeadline((Date)value);
                    break;
                case "is_done":
                    existingTodo.setDone((Boolean) value);
                    break;
                case "priority":
                    existingTodo.setPriority((Integer) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
        }
        return todoRepository.save(existingTodo);
    }
}
