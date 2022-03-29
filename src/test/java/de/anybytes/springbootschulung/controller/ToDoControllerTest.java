package de.anybytes.springbootschulung.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoServiceImpl toDoService;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    private List<ToDo> toDoList;

    @BeforeEach
    public void setup() {
        toDoList = new ArrayList<>(Arrays.asList(
                toDo1 = new ToDo(1L, false, "task1", null),
                toDo2 = new ToDo(1L, true, "task2", null),
                toDo3 = new ToDo(1L, false, "task3", null)
        ));

    }


    @Test
    @DisplayName("Test get single Todo")
    public void getTodo() throws Exception {
        when(toDoService.getTodo(1L)).thenReturn(this.toDo1);

        this.mockMvc.perform(get("/api/todo/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                "id": 1,
                                "isDone": false,
                                "name": "task1",
                                "dueDate": null
                                }
                        """))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.isDone").value(false))
                .andExpect(jsonPath("$.dueDate").isEmpty())
                .andExpect(jsonPath("$.name").value("task1"));
    }

    @Test
    @DisplayName("Test get all Todos")
    public void getTodos() throws Exception {
        when(toDoService.getTodos()).thenReturn(this.toDoList);

        this.mockMvc.perform(get("/api/todo/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[2].name").value("task3"));
    }

}
