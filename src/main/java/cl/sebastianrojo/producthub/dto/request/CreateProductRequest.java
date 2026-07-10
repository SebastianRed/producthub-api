package cl.sebastianrojo.producthub.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Request body used to create a new product.")
public class CreateProductRequest {

    @Schema(
            description = "Product name",
            example = "Mechanical Keyboard"
    )
    @NotBlank(message = "Product name is required")
    private String name;

    @Schema(
            description = "Product description",
            example = "RGB mechanical keyboard with blue switches"
    )
    private String description;

    @Schema(
            description = "Product price",
            example = "59990"
    )
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @Schema(
            description = "Available stock",
            example = "25",
            minimum = "0"
    )
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Schema(
            description = "Identifier of the category associated with the product",
            example = "2"
    )
    @NotNull(message = "Category id is required")
    private Long categoryId;

    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}