package com.primeur.stage.port;


import com.primeur.stage.domain.dto.Fruit;

import java.util.List;


public interface FruitStorageService {

	Fruit create(Fruit entity);

	Fruit update(Fruit entity, Integer id);

	void delete(Integer id);

	List<Fruit> getAll();
}
