package de.anybytes.springbootschulung.service;

import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    ToDoRepository toDoRepository;

    @Spy
    ModelMapper modelMapper;

    @InjectMocks
    ToDoServiceImpl toDoService;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    private UpdateToDoDTO updateToDoDTO;
    private CreateToDoDTO createToDoDTO;

    @BeforeEach
    public void setup() {
        toDo1 = new ToDo("task1", false);
        toDo2 = new ToDo("task2", true);
        toDo3 = new ToDo("task3", false);
        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3));

        updateToDoDTO = new UpdateToDoDTO(3L, true, "update", "01.01.2022");
    }

    @Test
    @DisplayName("Should return a Todo")
    public void getTodo() {
        when(toDoRepository.findById(1L)).thenReturn(Optional.of(toDo1));
        assertEquals(toDo1, toDoService.getTodo(1L));
    }

    @Test
    @DisplayName("Should return an Exception when no Todo were found")
    public void getTodoException() {
        when(toDoRepository.findById(4L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> toDoService.getTodo(4L));
    }

    @Test
    @DisplayName("Should update the ToDo")
    public void updateToDo() {
        ToDo mappedToDo = new ToDo("update", true);
        mappedToDo.setId(3L);
        mappedToDo.setDueDate("01.01.2022");

        when(toDoRepository.findById(3L)).thenReturn(Optional.of(toDo3));
        when(toDoRepository.save(any())).thenReturn(mappedToDo);
        assertEquals(mappedToDo, toDoService.updateTodo(updateToDoDTO));

    }

}
