package cl.sebastianrojo.producthub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a product category returned by the API.")
public record CategoryResponse(

        @Schema(
                description = "Category identifier",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Category name",
                example = "Electronics"
        )
        String name
) {}