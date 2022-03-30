package de.anybytes.springbootschulung.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA Entity Doku: https://spring.io/guides/gs/accessing-data-jpa/

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isDone = false;
    private String name;
    private String dueDate;

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
