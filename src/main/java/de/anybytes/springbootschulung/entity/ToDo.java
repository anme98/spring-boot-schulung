package de.anybytes.springbootschulung.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ToDo {

    @Id
    private Long id;

    public ToDo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
