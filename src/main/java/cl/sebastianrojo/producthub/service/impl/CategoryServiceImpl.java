package cl.sebastianrojo.producthub.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.sebastianrojo.producthub.dto.request.CreateCategoryRequest;
import cl.sebastianrojo.producthub.dto.response.CategoryResponse;
import cl.sebastianrojo.producthub.entity.Category;
import cl.sebastianrojo.producthub.repository.CategoryRepository;
import cl.sebastianrojo.producthub.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        Category category = new Category();
        category.setName(request.getName());

        Category saved = categoryRepository.save(category);

        return new CategoryResponse(
                saved.getId(),
                saved.getName()
        );
    }

    @Override
    public List<CategoryResponse> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName()
                ))
                .toList();
    }
}