package com.driver.service.impl;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

@Service
public class FoodServiceImpl implements FoodService{
    
	@Autowired
	FoodRepository repo;
	
	@Override
	public FoodDto createFood(FoodDto food) {
		// TODO Auto-generated method stub
		FoodEntity f=new FoodEntity();
		 f.setFoodCategory(food.getFoodCategory());
		 f.setFoodId(food.getFoodId());
		 f.setFoodName(food.getFoodName());
		 f.setFoodPrice(f.getFoodPrice());
		 repo.save(f);
		 return food;
	}

	@Override
	public FoodDto getFoodById(String foodId) throws Exception {
		// TODO Auto-generated method stub
		FoodEntity f=repo.findByFoodId(foodId);
		FoodDto food=new FoodDto();
		
		food.setFoodCategory(f.getFoodCategory());
		food.setFoodId(foodId);
		food.setFoodName(f.getFoodName());
		food.setFoodPrice(f.getFoodPrice());
		food.setId(f.getId());
		return food;
	}

	@Override
	public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
		// TODO Auto-generated method stub
		FoodEntity f=repo.findByFoodId(foodId);
		f.setFoodCategory(foodDetails.getFoodCategory());
		f.setFoodName(foodDetails.getFoodName());
		f.setFoodPrice(foodDetails.getFoodPrice());
		f.setFoodId(foodDetails.getFoodId());
		repo.save(f);
		return foodDetails;
	}

	@Override
	public void deleteFoodItem(String id) throws Exception {
		// TODO Auto-generated method stub
		FoodEntity f=repo.findByFoodId(id);
		repo.delete(f);
		
	}

	@Override
	public List<FoodDto> getFoods() {
		// TODO Auto-generated method stub
		List<FoodEntity> list=(List<FoodEntity>) repo.findAll();
		List<FoodDto> l=new ArrayList<>();
		for(FoodEntity f:list)
		{   FoodDto foodDetails=new FoodDto();
			f.setFoodCategory(foodDetails.getFoodCategory());
			f.setFoodName(foodDetails.getFoodName());
			f.setFoodPrice(foodDetails.getFoodPrice());
			f.setFoodId(foodDetails.getFoodId());
			f.setId(foodDetails.getId());
			l.add(foodDetails);
		}
		return l;
	}
	
}
