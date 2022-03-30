package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
