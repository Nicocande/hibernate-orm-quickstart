package com.primeur.stage.port;

import com.primeur.stage.domain.dto.Fruit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public interface FruitStorageService {

	Fruit create(Fruit fruit);

    @ApplicationScoped
    class FruitDAO implements FruitStorageService {

        @Inject
        EntityManager entityManager;

        @Override
        public Fruit create(Fruit fruit) {
            entityManager.persist(fruit);
            return fruit;
        }

    }
}
