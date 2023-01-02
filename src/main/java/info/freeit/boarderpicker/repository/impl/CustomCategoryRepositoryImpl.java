package info.freeit.boarderpicker.repository.impl;

import info.freeit.boarderpicker.entity.Category;
import info.freeit.boarderpicker.repository.CustomCategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Category findByName(String name) {
        try {
            return (Category) entityManager.createNativeQuery("SELECT c.id AS id, " +
                            "c.name AS name, FROM categories c WHERE c.name = ?", Category.class)
                    .setParameter(1, name)
                    .unwrap(org.hibernate.query.Query.class).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
