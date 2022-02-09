package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    // Übernimmt den Count anstatt alle DB-Tabellen zu lesen
    Long countByIsDone(Boolean isDone);

    // Arbeitet mit Reflektions
    List<ToDo> findAllByIsDone(Boolean isDone);

}
