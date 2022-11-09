package tech.wiktor.apis.kpbatapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wiktor.apis.kpbatapi.models.Image;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
    Optional<Image> findById(Long id);
    List<Image> findAll();
}
