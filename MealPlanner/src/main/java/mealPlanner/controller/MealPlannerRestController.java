package mealPlanner.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mealPlanner.dto.OwnedIngredientDto;
import mealPlanner.dto.UserDto;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.User;
import mealPlanner.service.InvalidInputException;
import mealPlanner.service.MealPlannerService;

@RestController 

public class MealPlannerRestController {

	@Autowired private MealPlannerService service;
	@Autowired private ModelMapper modelMapper;

	@PostMapping(value = { "/newUser/{username}/{password}/{calorieGoal}" })
	
	public UserDto createUser(
			@PathVariable("username") String username,
			@PathVariable("password") String password,
			@PathVariable("calorieGoal") int calorieGoal) throws InvalidInputException {
		
		User u = service.createUser(username, password, calorieGoal);
		return convertToDto(u); 
	}
	
	private UserDto convertToDto(User u) {

		return modelMapper.map(u, UserDto.class);
	}
	
	
	
	 @PostMapping(value = { "enterOwnedIngredients/{userName}/{ingredientName}/{amount}"}) 
	 
	 public  OwnedIngredientDto enterOwnIngredient(
			
			 @PathVariable("userName") String username,
			 @PathVariable("ingredientName") String ingredientName,
			 @PathVariable("amount") int amount) throws InvalidInputException {
	 
		OwnedIngredient own_i = service.enterOwnIngredient(username, ingredientName, amount);
			 
		return convertToDto(own_i); 
	}
	 
	 private OwnedIngredientDto convertToDto(OwnedIngredient i) {

			return modelMapper.map(i, OwnedIngredientDto.class);
		}

}