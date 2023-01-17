package info.freeit.boarderpicker.repository;

import info.freeit.boarderpicker.entity.Category;

public interface CustomCategoryRepository {

    Category findByName(String name);
}
