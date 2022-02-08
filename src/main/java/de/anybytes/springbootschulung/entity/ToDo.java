package de.anybytes.springbootschulung.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA Entity Doku: https://spring.io/guides/gs/accessing-data-jpa/

@Entity
@Data
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean isDone;
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
