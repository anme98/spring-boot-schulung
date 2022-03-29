package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.exception.ResourceNotFoundException;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ToDoServiceImpl(ToDoRepository toDoRepository, ModelMapper modelMapper) {
        this.toDoRepository = toDoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createToDo(CreateToDoDTO toDoDTO) {
        toDoRepository.save(modelMapper.map(toDoDTO, ToDo.class));
    }

    @Override
    public ToDo updateTodo(UpdateToDoDTO toDoDTO) {
        Long id = toDoDTO.getId();
        ToDo todo = toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        modelMapper.map(toDoDTO, todo);
        return toDoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDo getTodo(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }

    @Override
    public List<ToDo> getTodos() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getDoneTodos() {
        return toDoRepository.findAllByIsDone(true);
    }

    @Override
    public List<ToDo> getUndoneTodos() {
        return toDoRepository.findAllByIsDone(false);
    }

    @Override
    public Long countTodos(Boolean isDone) {
        return toDoRepository.countByIsDone(isDone);
    }

}
