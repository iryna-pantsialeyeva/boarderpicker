package info.freeit.boarderpicker.repository.impl;

import info.freeit.boarderpicker.entity.Producer;
import info.freeit.boarderpicker.repository.CustomProducerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomProducerRepositoryImpl implements CustomProducerRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Producer findByName(String name) {
        return (Producer) entityManager.createNativeQuery("SELECT p.id AS id, " +
                        "p.name AS name, FROM producers p WHERE p.name = ?", Producer.class)
                .setParameter(1, name)
                .unwrap(org.hibernate.query.Query.class).getSingleResult();
    }
}
