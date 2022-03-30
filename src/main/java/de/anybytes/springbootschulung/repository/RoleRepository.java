package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
