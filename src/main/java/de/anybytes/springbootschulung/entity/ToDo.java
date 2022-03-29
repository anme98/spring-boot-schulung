package de.anybytes.springbootschulung.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

// JPA Entity Doku: https://spring.io/guides/gs/accessing-data-jpa/

@Entity
@Data
@AllArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isDone = false;
    private String name;
    private String dueDate;

    protected ToDo() {
    }

    public ToDo(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", isDone=" + isDone +
                ", name='" + name + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
