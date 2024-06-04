package com.sl.demo.task;

/**
 * Task entity class that can be later convert to JPA POJO
 */
public class Task {

  private long id;
  private String title;
  private String description;
  private String status;

  public Task(long id, String title, String description, String status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
  }

  public Task(Task task) {
    this.id = task.id;
    this.title = task.title;
    this.description = task.description;
    this.status = task.status;
  }

  public long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatus() {
    return this.status;
  }

  public void updateTask(Task task){
    this.title = task.title;
    this.description = task.description;
    this.status = task. status;
  }

}