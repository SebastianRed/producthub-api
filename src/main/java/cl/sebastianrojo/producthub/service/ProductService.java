package cl.sebastianrojo.producthub.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import cl.sebastianrojo.producthub.dto.request.CreateProductRequest;
import cl.sebastianrojo.producthub.dto.request.UpdateProductRequest;
import cl.sebastianrojo.producthub.dto.response.ProductResponse;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    ProductResponse findById(Long id);

    Page<ProductResponse> findAll(
        Long categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Integer stockMin,
        Boolean active,
        int page,
        int size,
        String sort
);

    ProductResponse update(Long id, UpdateProductRequest request);

    void delete(Long id);

    Page<ProductResponse> findLowStock(
            Integer threshold,
            int page,
            int size
    );
}