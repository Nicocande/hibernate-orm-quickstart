package com.primeur.stage.domain.port;

import com.primeur.stage.domain.dto.Fruit;
import com.primeur.stage.port.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class FruitDAO implements FruitStorageService {


    @Inject
    EntityManager entityManager;

    @Override
    public Fruit create(Fruit entity) {
        entityManager.persist(entity);
        return entity;

    }

    @Override
    public Fruit update(Fruit entity, Integer id) {

        Fruit putFruit = entityManager.find(Fruit.class, id);
        putFruit.setName(entity.getName());

        return putFruit;
    }


    @Override
    public void delete(Integer id) {
        Fruit fruit = findBYId(id);
        entityManager.remove(fruit);
    }

    @Override
    public List<Fruit> getAll() {
        return entityManager.createNamedQuery("Fruits.findAll", Fruit.class)
                .getResultList();
    }

    public Fruit findBYId(Integer id) {

        return entityManager.find(Fruit.class, id);

    }


}
