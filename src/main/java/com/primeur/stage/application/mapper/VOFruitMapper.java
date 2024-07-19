package com.primeur.stage.application.mapper;

import java.util.Date;

import com.primeur.stage.application.vo.VOFruit;
import com.primeur.stage.domain.dto.Fruit;

public class VOFruitMapper {

	VOFruit vo;
	
	public VOFruitMapper(Fruit fruit) {
		this.vo = convertEntity(fruit);
	}
	
	private VOFruit convertEntity(Fruit fruit) {
		VOFruit vo = new VOFruit();
		vo.setId(""+ fruit.getId());
		vo.setName(fruit.getName());
		vo.setDescription("MY DESC " + fruit.getName());
		vo.setCurrentDate(new Date());
		return vo;
	}


	public VOFruit getEntity() {
		return this.vo;
	}
	
}
