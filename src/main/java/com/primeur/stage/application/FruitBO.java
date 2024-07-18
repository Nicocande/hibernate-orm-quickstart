package com.primeur.stage.application;

import com.primeur.stage.application.mapper.EntFruitMapper;
import com.primeur.stage.application.mapper.VOFruitMapper;
import com.primeur.stage.application.vo.VOFruit;
import com.primeur.stage.domain.dto.Fruit;
import com.primeur.stage.domain.port.FruitService;
import com.primeur.stage.port.FruitStorageService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FruitBO implements FruitService {

	@Inject
	FruitStorageService fruitStorageService;
	
	
	@Override
	public VOFruit create(VOFruit voFruit) {
		EntFruitMapper entMapper = new EntFruitMapper(voFruit);
		Fruit entFruit = fruitStorageService.create(entMapper.getEntity());
		
//		VOFruit test = new VOFruit();
//		test.setId("" + entFruit.getId());
//		test.setName(entFruit.getName());
		
		VOFruitMapper mapperVo = new VOFruitMapper(entFruit);
		return mapperVo.getEntity();
	}

}
