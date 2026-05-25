package cl.sebastianrojo.producthub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.sebastianrojo.producthub.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}