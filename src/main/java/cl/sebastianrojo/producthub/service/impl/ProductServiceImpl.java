package cl.sebastianrojo.producthub.service.impl;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import cl.sebastianrojo.producthub.dto.request.CreateProductRequest;
import cl.sebastianrojo.producthub.dto.request.UpdateProductRequest;
import cl.sebastianrojo.producthub.dto.response.ProductResponse;
import cl.sebastianrojo.producthub.entity.Category;
import cl.sebastianrojo.producthub.entity.Product;
import cl.sebastianrojo.producthub.exception.ResourceNotFoundException;
import cl.sebastianrojo.producthub.repository.CategoryRepository;
import cl.sebastianrojo.producthub.repository.ProductRepository;
import cl.sebastianrojo.producthub.repository.specification.ProductSpecification;
import cl.sebastianrojo.producthub.service.ProductService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getActive(),
                product.getCategory().getId(),
                product.getCategory().getName());
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found"));

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setActive(true);
        product.setCategory(category);

        return mapToResponse(
                productRepository.save(product));
    }

    @Override
    public ProductResponse findById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found"));

        return mapToResponse(product);
    }

    @Override
    public ProductResponse update(
            Long id,
            UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

        return mapToResponse(
                productRepository.save(product));
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found"));

        product.setActive(false);

        productRepository.save(product);
    }

    @Override
    public Page<ProductResponse> findAll(
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer stockMin,
            Boolean active,
            int page,
            int size,
            String sort) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort).ascending());

        Specification<Product> spec = Specification.where(
                ProductSpecification.hasCategory(categoryId))
                .and(
                        ProductSpecification.hasMinPrice(minPrice))
                .and(
                        ProductSpecification.hasMaxPrice(maxPrice))
                .and(
                        ProductSpecification.hasMinimumStock(stockMin))
                .and(
                        ProductSpecification.isActive(active));

        return productRepository
                .findAll(spec, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<ProductResponse> findLowStock(
            Integer threshold,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Product> spec = (root, query, cb) -> cb.lessThanOrEqualTo(
                root.get("stock"),
                threshold);

        return productRepository
                .findAll(spec, pageable)
                .map(this::mapToResponse);
    }

}