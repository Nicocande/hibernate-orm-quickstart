package com.primeur.stage.domain.port;

import com.primeur.stage.application.vo.VOFruit;

import java.util.List;


public interface FruitService {


	VOFruit create(VOFruit voFruit);

	VOFruit update(VOFruit voFruit, String id);

	void delete(String id);

	List<VOFruit> getAll();

}
