package cl.sebastianrojo.producthub.dto.response;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a product returned by the API.")
public record ProductResponse(

        @Schema(description = "Product identifier", example = "1")
        Long id,

        @Schema(description = "Product name", example = "Mechanical Keyboard")
        String name,

        @Schema(
                description = "Product description",
                example = "RGB mechanical keyboard with blue switches"
        )
        String description,

        @Schema(description = "Product price", example = "59990")
        BigDecimal price,

        @Schema(
                description = "Available stock",
                example = "25",
                minimum = "0"
        )
        Integer stock,

        @Schema(
                description = "Indicates whether the product is active",
                example = "true"
        )
        Boolean active,

        @Schema(
                description = "Identifier of the associated category",
                example = "2"
        )
        Long categoryId,

        @Schema(
                description = "Name of the associated category",
                example = "Electronics"
        )
        String categoryName

) {}