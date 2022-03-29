package de.anybytes.springbootschulung.controller;

import de.anybytes.springbootschulung.aspect.TestAnnotation;
import de.anybytes.springbootschulung.aspect.TrackExecutionTime;
import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<ToDo> updateTodo(@RequestBody UpdateToDoDTO todo) {
        return ResponseEntity.ok(toDoService.updateTodo(todo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

    @TrackExecutionTime
    @GetMapping("/get/{id}")
    public ResponseEntity<ToDo> getTodo(@PathVariable Long id) {
        return ResponseEntity.ok(toDoService.getTodo(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ToDo>> getAllTodos() {
        return ResponseEntity.ok(toDoService.getTodos());
    }

    @TestAnnotation
    @GetMapping("/getDone")
    public ResponseEntity<List<ToDo>> getDoneTodos() {
        return ResponseEntity.ok(toDoService.getDoneTodos());
    }

    @GetMapping("/getUndone")
    public ResponseEntity<List<ToDo>> getUndoneTodos() {
        return ResponseEntity.ok(toDoService.getUndoneTodos());
    }

    @GetMapping("/countUndoneTodos")
    public ResponseEntity<Long> countUndoneTodos() {
        return ResponseEntity.ok(toDoService.countTodos(false));
    }

    @GetMapping("/countDoneTodos")
    public ResponseEntity<Long> countDoneTodos() {
        return ResponseEntity.ok(toDoService.countTodos(true));
    }

}
