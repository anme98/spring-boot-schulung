package de.anybytes.springbootschulung.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.anybytes.springbootschulung.dto.CreateToDoDTO;
import de.anybytes.springbootschulung.dto.UpdateToDoDTO;
import de.anybytes.springbootschulung.entity.ToDo;
import de.anybytes.springbootschulung.service.ToDoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoServiceImpl toDoService;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    private List<ToDo> toDoList;
    private CreateToDoDTO createToDoDTO;
    private UpdateToDoDTO updateToDoDTO;

    @BeforeEach
    public void setup() {
        toDoList = new ArrayList<>(Arrays.asList(
                toDo1 = new ToDo(1L, false, "task1", null),
                toDo2 = new ToDo(1L, true, "task2", null),
                toDo3 = new ToDo(1L, false, "task3", null)
        ));
        createToDoDTO = new CreateToDoDTO(true, "update", "01.01.2022");
        updateToDoDTO = new UpdateToDoDTO(1L, true, "update", "01.01.2020");
    }

    @Test
    @DisplayName("Should save one Todo to DB")
    public void createTodo() throws Exception {
        this.mockMvc.perform(post("/api/todo/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createToDoDTO))
                )
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test get single Todo")
    public void getTodo() throws Exception {
        when(toDoService.getTodo(1L)).thenReturn(this.toDo1);

        this.mockMvc.perform(get("/api/todo/get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                "id": 1,
                                "isDone": false,
                                "name": "task1",
                                "dueDate": null
                                }
                        """));

        this.mockMvc.perform(get("/api/todo/get/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.isDone").value(false))
                .andExpect(jsonPath("$.dueDate").isEmpty())
                .andExpect(jsonPath("$.name").value("task1"));
    }

    @Test
    @DisplayName("Test get all Todos")
    public void getAllTodos() throws Exception {
        when(toDoService.getTodos()).thenReturn(this.toDoList);

        this.mockMvc.perform(get("/api/todo/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].name").value("task3"));
    }

    @Test
    void updateTodo() throws Exception {
        when(toDoService.updateTodo(updateToDoDTO)).thenReturn(toDo1);

        this.mockMvc.perform(put("/api/todo/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateToDoDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.isDone").value(false))
                .andExpect(jsonPath("$.dueDate").isEmpty())
                .andExpect(jsonPath("$.name").value("task1"));
    }

    @Test
    void deleteTodo() throws Exception {
        this.mockMvc.perform(delete("/api/todo/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void getDoneTodos() throws Exception {
        when(toDoService.getDoneTodos()).thenReturn(toDoList);

        this.mockMvc.perform(get("/api/todo/getDone")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].name").value("task3"));
    }

    @Test
    void getUndoneTodos() throws Exception {
        when(toDoService.getUndoneTodos()).thenReturn(toDoList);

        this.mockMvc.perform(get("/api/todo/getUndone")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].name").value("task3"));
    }

    @Test
    void countUndoneTodos() throws Exception {
        when(toDoService.countTodos(false)).thenReturn(2L);

        this.mockMvc.perform(get("/api/todo/countUndoneTodos")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2));

    }

    @Test
    void countDoneTodos() throws Exception {
        when(toDoService.countTodos(true)).thenReturn(1L);

        this.mockMvc.perform(get("/api/todo/countDoneTodos")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }
}
