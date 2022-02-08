package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.entity.ToDo;

import java.util.List;

public interface ToDoService {
    void createToDo(ToDo toDo);
    void updateTodo(ToDo toDo, String id);
    void deleteTodo(String id);
    ToDo getTodo(String id);
    List<ToDo> getTodos();
    List<ToDo> getDoneTodos();
    List<ToDo> getUndoneTodos();
    Long countTodos(Boolean isDone);
}
