package cl.sebastianrojo.producthub.dto.error;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a standard API error response.")
public record ErrorResponse(

        @Schema(description = "Date and time when the error occurred")
        LocalDateTime timestamp,

        @Schema(description = "HTTP status code", example = "404")
        int status,

        @Schema(description = "HTTP status name", example = "Not Found")
        String error,

        @Schema(description = "Error message", example = "Product not found")
        String message

) {}