package de.anybytes.springbootschulung.repository;

import de.anybytes.springbootschulung.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    void deleteById(Long id);

}
