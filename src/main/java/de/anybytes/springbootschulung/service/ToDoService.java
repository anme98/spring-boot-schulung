package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoService {
    ResponseEntity<Void> createToDo(CreateToDoDTO toDo);
    ResponseEntity<ToDo> updateTodo(UpdateToDoDTO toDo);
    ResponseEntity<Void> deleteTodo(Long id);
    ResponseEntity<ToDo> getTodo(Long id);
    ResponseEntity<List<ToDo>> getTodos();
    ResponseEntity<List<ToDo>> getDoneTodos();
    ResponseEntity<List<ToDo>> getUndoneTodos();
    ResponseEntity<Long> countTodos(Boolean isDone);
}
