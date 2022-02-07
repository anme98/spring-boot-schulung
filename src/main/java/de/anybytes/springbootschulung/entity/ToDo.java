package de.anybytes.springbootschulung.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA Entity Doku: https://spring.io/guides/gs/accessing-data-jpa/

@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Boolean isDone;
    private String name;
    private String dueDate;

    protected ToDo() {
    }

    public ToDo(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
