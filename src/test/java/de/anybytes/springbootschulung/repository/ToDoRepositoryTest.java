package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //
public class ToDoRepositoryTest {

    @Autowired
    private  ToDoRepository toDoRepository;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    @BeforeEach
    public void prepare(){
        toDo1 = new ToDo("task1", false);
        toDo2 = new ToDo("task2", true);
        toDo3 = new ToDo("task3", false);
        toDoRepository.saveAll(Arrays.asList(toDo1,toDo2,toDo3));
    }

    @Test
    @DisplayName("shouldTestCountByIsDone")
    public void countByIsDone(){
        Long actualCount = toDoRepository.countByIsDone(true);
        assertEquals(1, actualCount);
    }

    @Test
    @DisplayName("shouldTestFindAllByIsDone")
    public void findAllByIsDone() throws Exception {
        List<ToDo> toDoList = toDoRepository.findAllByIsDone(true);
        List<ToDo> expectedToDoList = Arrays.asList(toDo2);
        assertEquals(expectedToDoList, toDoList);
    }
}
