package info.freeit.boarderpicker.service.impl;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.repository.CategoryRepository;
import info.freeit.boarderpicker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory (Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByName (String name) {
        return categoryRepository.findByName(name);
    }
}
