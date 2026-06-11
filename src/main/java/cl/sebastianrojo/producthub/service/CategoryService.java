package cl.sebastianrojo.producthub.service;

import java.util.List;

import cl.sebastianrojo.producthub.dto.request.CreateCategoryRequest;
import cl.sebastianrojo.producthub.dto.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    List<CategoryResponse> findAll();
}