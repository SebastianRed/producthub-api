package cl.sebastianrojo.producthub.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a validation error response.")
public record ValidationErrorResponse(

        @Schema(description = "Date and time when the error occurred")
        LocalDateTime timestamp,

        @Schema(description = "HTTP status code", example = "400")
        int status,

        @Schema(description = "HTTP status name", example = "Bad Request")
        String error,

        @Schema(
                description = "Validation errors grouped by field",
                example = """
                {
                  "name": "Product name is required",
                  "price": "Price must be greater than 0"
                }
                """
        )
        Map<String, String> errors

) {}