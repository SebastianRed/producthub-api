package cl.sebastianrojo.producthub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body used to create a new product category.")
public class CreateCategoryRequest {

    @Schema(
            description = "Category name",
            example = "Electronics"
    )
    @NotBlank(message = "Category name is required")
    private String name;

    public CreateCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}