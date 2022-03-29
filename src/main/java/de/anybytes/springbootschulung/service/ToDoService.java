package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;

import java.util.List;

public interface ToDoService {
    void createToDo(CreateToDoDTO toDo);
    ToDo updateTodo(UpdateToDoDTO toDo);
    void deleteTodo(Long id);
    ToDo getTodo(Long id);
    List<ToDo> getTodos();
    List<ToDo> getDoneTodos();
    List<ToDo> getUndoneTodos();
    Long countTodos(Boolean isDone);
}
