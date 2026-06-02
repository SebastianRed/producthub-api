package cl.sebastianrojo.producthub.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Boolean active,
        Long categoryId,
        String categoryName
) {}