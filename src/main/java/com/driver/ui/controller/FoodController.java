package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	FoodService service;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
        FoodDto foodDto=service.getFoodById(id) ;
        FoodDetailsResponse response=new FoodDetailsResponse();
        response.setFoodId(foodDto.getFoodId());
        response.setFoodName(foodDto.getFoodName());
        response.setFoodPrice(foodDto.getFoodPrice());
        response.setFoodCategory(foodDto.getFoodCategory());
		return response;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
		FoodDetailsResponse reposne=new FoodDetailsResponse();
		FoodDto food=new FoodDto();
		food.setFoodCategory(foodDetails.getFoodCategory());
		food.setFoodName(foodDetails.getFoodName());
		food.setFoodPrice(foodDetails.getFoodPrice());
		food.setFoodId(foodDetails.getFoodId());
		FoodDto f=service.createFood(food);
		reposne.setFoodCategory(f.getFoodCategory());
		reposne.setFoodName(f.getFoodName());
		reposne.setFoodPrice(f.getFoodPrice());
		reposne.setFoodId(f.getFoodId());
		
		
		
		
		return reposne;
		
	   
	  
		
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDetailsResponse reposne=new FoodDetailsResponse();
		FoodDto food=new FoodDto();
		food.setFoodCategory(foodDetails.getFoodCategory());
		food.setFoodName(foodDetails.getFoodName());
		food.setFoodPrice(foodDetails.getFoodPrice());
		FoodDto f=service.updateFoodDetails(id, food);
		reposne.setFoodCategory(f.getFoodCategory());
		reposne.setFoodName(f.getFoodName());
		reposne.setFoodPrice(f.getFoodPrice());
		reposne.setFoodId(f.getFoodId());
		return reposne;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
         service.deleteFoodItem(id);
         OperationStatusModel model=new OperationStatusModel();
         model.setOperationName("DELETE");
         model.setOperationResult("SUCCESS");
         return model;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
       List<FoodDto> list =service.getFoods();
       List<FoodDetailsResponse>l=new ArrayList<>();
       for(FoodDto food: list)
       {
    	   FoodDetailsResponse reposne=new FoodDetailsResponse();
    	   reposne.setFoodCategory(food.getFoodCategory());
   		   reposne.setFoodName(food.getFoodName());
   		   reposne.setFoodPrice(food.getFoodPrice());
   		   reposne.setFoodId(food.getFoodId());
   		   l.add(reposne);
       }
		return l;
	}
}
