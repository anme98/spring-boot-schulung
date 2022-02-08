package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    Long countByIsDone(Boolean isDone);
}
