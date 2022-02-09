package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.exception.ResourceNotFoundException;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<Void> createToDo(CreateToDoDTO toDoDTO) {
        ToDo todo = toDoRepository.save(modelMapper.map(toDoDTO, ToDo.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ToDo> updateTodo(UpdateToDoDTO toDoDTO) {
        Long id = toDoDTO.getId();
        ToDo todo = toDoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Not found Todo with id = " + id)
        );
        modelMapper.map(toDoDTO, todo);
        return new ResponseEntity<>(toDoRepository.save(todo), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteTodo(Long id) {
        toDoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ToDo> getTodo(Long id) {
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Todo with id = " + id));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ToDo>> getTodos() {
        return new ResponseEntity<>((List<ToDo>) toDoRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ToDo>> getDoneTodos() {
        return new ResponseEntity<>(toDoRepository.findAllByIsDone(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ToDo>> getUndoneTodos() {
        return new ResponseEntity<>(toDoRepository.findAllByIsDone(false), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> countTodos(Boolean isDone) {
        return new ResponseEntity<>(toDoRepository.countByIsDone(isDone), HttpStatus.OK);
    }

}
