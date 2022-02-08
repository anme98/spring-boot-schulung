package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void createToDo(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    @Override
    public void updateTodo(ToDo todo, String id) {
        ToDo currentTodo = toDoRepository.findById(Long.valueOf(id)).orElse(null);
        currentTodo.setIsDone(todo.getIsDone());
        currentTodo.setName(todo.getName());
        currentTodo.setDueDate(todo.getDueDate());
        toDoRepository.save(currentTodo);
    }

    @Override
    public void deleteTodo(String id) {
        toDoRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public ToDo getTodo(String id) {
        return toDoRepository.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public List<ToDo> getTodos() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getDoneTodos() {
        Iterable<ToDo> all = toDoRepository.findAll();
        List<ToDo> doneTodos = new LinkedList<>();
        for (ToDo toDo : all) {
            if (toDo.getIsDone()) {
                doneTodos.add(toDo);
            }
        }
        return doneTodos;
    }

    @Override
    public List<ToDo> getUndoneTodos() {
        Iterable<ToDo> all = toDoRepository.findAll();
        List<ToDo> doneTodos = new LinkedList<>();
        for (ToDo toDo : all) {
            if (!toDo.getIsDone()) {
                doneTodos.add(toDo);
            }
        }
        return doneTodos;
    }

    @Override
    public Long countTodos(Boolean isDone) {
        return toDoRepository.countByIsDone(isDone);
    }

}
