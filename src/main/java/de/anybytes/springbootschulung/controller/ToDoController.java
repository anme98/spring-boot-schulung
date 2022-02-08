package de.anybytes.springbootschulung.controller;

import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/save")
    public void createTodo(@RequestBody ToDo todo) {
        toDoService.createToDo(todo);
    }

    @PutMapping("/update/{id}")
    public void updateTodo(@RequestBody ToDo todo, @PathVariable String id) {
        toDoService.updateTodo(todo, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable String id) {
        toDoService.deleteTodo(id);
    }

    @GetMapping("/get/{id}")
    public ToDo getTodo(@PathVariable String id){
        return toDoService.getTodo(id);
    }

    @GetMapping("/getAll")
    public List<ToDo> getAllTodos(){
        return toDoService.getTodos();
    }

    @GetMapping("/getDone")
    public List<ToDo> getDoneTodos() {
        return toDoService.getDoneTodos();
    }

    @GetMapping("/getUndone")
    public List<ToDo> getUndoneTodos() {
        return toDoService.getDoneTodos();
    }

    @GetMapping("/countUndoneTodos")
    public Long countUndoneTodos() {
        return toDoService.countTodos(false);
    }

    @GetMapping("/countDoneTodos")
    public Long countDoneTodos() {
        return toDoService.countTodos(true);
    }

}
