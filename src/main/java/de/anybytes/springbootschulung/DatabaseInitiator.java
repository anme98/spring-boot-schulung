package de.anybytes.springbootschulung;

import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInitiator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    @Autowired
    public DatabaseInitiator(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void run(String... args) {
        ToDo todo1 = new ToDo("Eiere kaufen", true);
        ToDo todo2 = new ToDo("Spring Boot lernen", false);
        ToDo todo3 = new ToDo("Sleep", true);
        ToDo todo4 = new ToDo("Repeat", false);
        todo1.setIsDone(true);
        toDoRepository.saveAll(Arrays.asList(todo1, todo2, todo3, todo4));
    }
}
