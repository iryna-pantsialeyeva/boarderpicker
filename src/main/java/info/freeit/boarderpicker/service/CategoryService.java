package info.freeit.boarderpicker.service;

import info.freeit.boarderpicker.entity.Category;

public interface CategoryService {

    void saveCategory (Category category);
    Category getCategoryByName (String name);
}
