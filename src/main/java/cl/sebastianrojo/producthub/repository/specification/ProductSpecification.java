package cl.sebastianrojo.producthub.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import cl.sebastianrojo.producthub.entity.Product;

public class ProductSpecification {

        private ProductSpecification() {
        }

        public static Specification<Product> hasCategory(Long categoryId) {
                return (root, query, cb) -> categoryId == null
                                ? null
                                : cb.equal(
                                                root.get("category").get("id"),
                                                categoryId);
        }

        public static Specification<Product> hasMinimumStock(Integer stockMin) {
                return (root, query, cb) -> stockMin == null
                                ? null
                                : cb.greaterThanOrEqualTo(
                                                root.get("stock"),
                                                stockMin);
        }

        public static Specification<Product> isActive(Boolean active) {
                return (root, query, cb) -> active == null
                                ? null
                                : cb.equal(
                                                root.get("active"),
                                                active);
        }

        public static Specification<Product> hasMinPrice(BigDecimal minPrice) {

                return (root, query, cb) -> minPrice == null
                                ? null
                                : cb.greaterThanOrEqualTo(
                                                root.get("price"),
                                                minPrice);
        }

        public static Specification<Product> hasMaxPrice(BigDecimal maxPrice) {

                return (root, query, cb) -> maxPrice == null
                                ? null
                                : cb.lessThanOrEqualTo(
                                                root.get("price"),
                                                maxPrice);
        }
}