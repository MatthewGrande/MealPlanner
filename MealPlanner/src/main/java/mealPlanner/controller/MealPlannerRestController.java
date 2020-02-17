package mealPlanner.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mealPlanner.dto.MealDto;
import mealPlanner.dto.OwnedIngredientDto;
import mealPlanner.dto.UserDto;
import mealPlanner.model.Meal;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.User;
import mealPlanner.service.InvalidInputException;
import mealPlanner.service.MealPlannerService;

@RestController

public class MealPlannerRestController {

	@Autowired
	private MealPlannerService service;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = { "/newUser/{username}/","/newUser/{username}" })

	public UserDto createUser(@PathVariable("username") String username, @RequestParam("password") String password,
			@RequestParam("calorieGoal") int calorieGoal) throws InvalidInputException {

		User u = service.createUser(username, password, calorieGoal);
		return convertToDto(u);
	}

	@PostMapping(value = { "/deleteUser/{username}/", "/deleteUser/{username}" })

	public UserDto deleteUser(@PathVariable("username") String username, @RequestParam("password") String password)
			throws InvalidInputException {

		User u = service.deleteUser(username, password);

		return convertToDto(u);
	}

	private UserDto convertToDto(User u) {

		return modelMapper.map(u, UserDto.class);
	}

	@PostMapping(value = { "/Login/{userName}/", "/Login/{userName}" })
	public void Login(@PathVariable("userName") String userName, @RequestParam("password") String password) {
		Boolean answer;
		try {
			answer = service.isValidLogin(userName, password);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@PostMapping(value = { "enterOwnedIngredients/{userName}/", "enterOwnedIngredients/{userName}" })

	public OwnedIngredientDto enterOwnIngredient(

			@PathVariable("userName") String username, @RequestParam("ingredientName") String ingredientName,
			@RequestParam("amount") int amount) throws InvalidInputException {

		OwnedIngredient own_i = service.enterOwnIngredient(username, ingredientName, amount);

		return convertToDto(own_i);
	}

	private OwnedIngredientDto convertToDto(OwnedIngredient i) {

		return modelMapper.map(i, OwnedIngredientDto.class);
	}
	
	@PostMapping(value = { "logMeal/{username}/", "logMeal/{username}" })

	public MealDto logMeal(

			@PathVariable("username") String username, 
			@RequestParam("recipe") int recipeIndex,
			@RequestParam("amount") int amount) throws InvalidInputException {

		Meal m = service.logMeal(username, recipeIndex, amount);
		
		return convertToDto(m);
	}
	private MealDto convertToDto(Meal m) {

		return modelMapper.map(m, MealDto.class);
	}

}