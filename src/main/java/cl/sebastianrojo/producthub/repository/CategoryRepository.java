package cl.sebastianrojo.producthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sebastianrojo.producthub.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameIgnoreCase(String name);
}