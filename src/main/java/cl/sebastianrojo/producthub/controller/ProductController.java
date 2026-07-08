package cl.sebastianrojo.producthub.controller;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.sebastianrojo.producthub.dto.request.CreateProductRequest;
import cl.sebastianrojo.producthub.dto.request.UpdateProductRequest;
import cl.sebastianrojo.producthub.dto.response.ProductResponse;
import cl.sebastianrojo.producthub.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Products", description = "Endpoints for managing products.")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a product", description = "Creates a new product in the inventory.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @Operation(summary = "Get product by ID", description = "Returns a product by its identifier.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ProductResponse findById(
            @Parameter(description = "Product identifier", example = "1") @PathVariable Long id) {
        return productService.findById(id);
    }

    @Operation(summary = "List products", description = "Returns a paginated list of products with optional filters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid filter or sort parameter")
    })
    @GetMapping
    public Page<ProductResponse> findAll(

            @Parameter(description = "Category identifier", example = "2") @RequestParam(required = false) Long categoryId,

            @Parameter(description = "Minimum product price", example = "1000") @RequestParam(required = false) BigDecimal minPrice,

            @Parameter(description = "Maximum product price", example = "5000") @RequestParam(required = false) BigDecimal maxPrice,

            @Parameter(description = "Minimum available stock", example = "5") @RequestParam(required = false) Integer stockMin,

            @Parameter(description = "Product status", example = "true") @RequestParam(required = false) Boolean active,

            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Sorting format: field,direction. Allowed fields: id, name, price, stock.", example = "price,desc") @RequestParam(defaultValue = "name") String sort) {

        return productService.findAll(
                categoryId,
                minPrice,
                maxPrice,
                stockMin,
                active,
                page,
                size,
                sort);
    }

    @Operation(summary = "Update a product", description = "Updates an existing product.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Product or category not found")
    })
    @PutMapping("/{id}")
    public ProductResponse update(

            @Parameter(description = "Product identifier", example = "1") @PathVariable Long id,

            @Valid @RequestBody UpdateProductRequest request) {

        return productService.update(id, request);
    }

    @Operation(summary = "Delete a product", description = "Performs a soft delete by marking the product as inactive.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(

            @Parameter(description = "Product identifier", example = "1") @PathVariable Long id) {

        productService.delete(id);
    }

    @Operation(summary = "List low stock products", description = "Returns products whose stock is less than or equal to the specified threshold.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    })
    @GetMapping("/low-stock")
    public Page<ProductResponse> lowStock(

            @Parameter(description = "Maximum stock threshold", example = "10") @RequestParam(defaultValue = "10") Integer threshold,

            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") int size) {

        return productService.findLowStock(
                threshold,
                page,
                size);
    }
}