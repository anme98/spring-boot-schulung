package de.anybytes.springbootschulung.controller;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.service.ToDoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> createTodo(@Valid @RequestBody CreateToDoDTO todo) {
        toDoService.createToDo(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ToDo> updateTodo(@RequestBody UpdateToDoDTO todo) {
        toDoService.updateTodo(todo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ToDo> getTodo(@PathVariable Long id) {
        return toDoService.getTodo(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ToDo>> getAllTodos() {
        return toDoService.getTodos();
    }

    @GetMapping("/getDone")
    public ResponseEntity<List<ToDo>> getDoneTodos() {
        return toDoService.getDoneTodos();
    }

    @GetMapping("/getUndone")
    public ResponseEntity<List<ToDo>> getUndoneTodos() {
        return toDoService.getUndoneTodos();
    }

    @GetMapping("/countUndoneTodos")
    public ResponseEntity<Long> countUndoneTodos() {
        return toDoService.countTodos(false);
    }

    @GetMapping("/countDoneTodos")
    public ResponseEntity<Long> countDoneTodos() {
        return toDoService.countTodos(true);
    }

}
