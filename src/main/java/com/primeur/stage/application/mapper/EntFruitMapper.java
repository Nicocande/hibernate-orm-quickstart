package com.primeur.stage.application.mapper;

import com.primeur.stage.application.vo.VOFruit;
import com.primeur.stage.domain.dto.Fruit;

public class EntFruitMapper {

	Fruit entity;
	
	public EntFruitMapper(VOFruit fruit) {
		this.entity = convertEntity(fruit);
	}
	
	private Fruit convertEntity(VOFruit fruit) {
		Fruit ent = new Fruit();
		ent.setName(fruit.getName());
		return ent;
	}


	public Fruit getEntity() {
		return this.entity;
	}
	
}
