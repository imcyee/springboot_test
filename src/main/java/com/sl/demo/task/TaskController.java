package com.sl.demo.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sl.demo.exception.ResourceNotFoundException;
import com.sl.demo.exception.BadRequestException;

/**
 * TODO:
 * 
 * Add service instead of accessing in controller
 * 
 */
@RestController
public class TaskController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // temporary cache for tasks
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTaskList() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Get all task
     * 
     * @param name
     * @return
     */
    @GetMapping("/tasks")
    public List<Task> tasks() {
        return getTaskList();
    }

    /**
     * Get task by id
     * 
     * @param name
     * @return
     */
    @GetMapping("/task")
    public Task task(@RequestParam(value = "id") Long id) {

        if (id == null) {
            throw new BadRequestException("Id cannot be empty");
        }

        Task found = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                found = task;
                break; // Once found, exit the loop
            }
        }
        return found;
    }

    /**
     * Create a new task and add to list
     * 
     * @param task
     * @return
     */
    @PostMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
    public Task createTask(@RequestBody Task task) {

        // TODO: add validation task

        Task newTask = new Task(task);
        tasks.add(newTask);

        return newTask;
    }

    /**
     * Update task
     * unlike patch this one has all info and update all info
     */
    @PutMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
    public String updateTask(@RequestBody Task task) {

        if (task == null) {
            throw new BadRequestException("Id cannot be empty");
        }

        Task found = null;
        for (Task _task : tasks) {
            if (_task.getId() == task.getId()) {
                found = _task;
                break; // Once found, exit the loop
            }
        }

        if (found == null) {
            throw new ResourceNotFoundException("No resource found for ID: " +  task.getId());
        }

        found.updateTask(task);
        return "Okay";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}