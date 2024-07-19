package com.primeur.stage.application.vo;

import com.primeur.stage.application.mapper.EntFruitMapper;
import com.primeur.stage.application.mapper.VOFruitMapper;
import com.primeur.stage.domain.dto.Fruit;
import com.primeur.stage.domain.port.FruitService;
import com.primeur.stage.port.FruitStorageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class FruitBO implements FruitService {


    @Inject
    FruitStorageService fruitStorageService;


    public VOFruit create(VOFruit voFruit) {
        EntFruitMapper entMapper = new EntFruitMapper(voFruit);
        Fruit entFruit = fruitStorageService.create(entMapper.getEntity());
        VOFruitMapper mapperVO = new VOFruitMapper(entFruit);
        return mapperVO.getEntity();
    }

    @Override
    public VOFruit update(VOFruit voFruit, String id) {
        EntFruitMapper entMapper = new EntFruitMapper(voFruit);
        Fruit entFruit = fruitStorageService.update(entMapper.getEntity(), getConvertedInt(id));
        VOFruitMapper mapperVO = new VOFruitMapper(entFruit);
        return mapperVO.getEntity();
    }

    @Override
    public void delete(String id) {

        int i = getConvertedInt(id);
        fruitStorageService.delete(i);

    }

    private int getConvertedInt(String id) {

        return Integer.parseInt(id);
    }

    private String getConvertedString(Integer id) {

        return String.valueOf(id);
    }

    @Override
    public List<VOFruit> getAll() {
        List<Fruit> fruitList = fruitStorageService.getAll();
        List<VOFruit> voFruitList = new ArrayList<>();

        for (Fruit fruit : fruitList) {
            VOFruitMapper voFruitMapper = new VOFruitMapper(fruit);
            VOFruit voFruit = voFruitMapper.getEntity();
            voFruitList.add(voFruit);

        }
        return voFruitList;
    }

}






