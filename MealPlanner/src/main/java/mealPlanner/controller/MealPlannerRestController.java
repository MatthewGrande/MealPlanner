package mealPlanner.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mealPlanner.dto.MealDto;
import mealPlanner.dto.OwnedIngredientDto;
import mealPlanner.dto.RecipeDto;
import mealPlanner.dto.UserDto;
import mealPlanner.model.DietType;
import mealPlanner.model.Meal;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.Recipe;
import mealPlanner.model.User;
import mealPlanner.service.InvalidInputException;
import mealPlanner.service.MealPlannerService;

@RestController

public class MealPlannerRestController {

	@Autowired
	private MealPlannerService service;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value = { "/newUser/{username}/", "/newUser/{username}" })

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

	@PostMapping(value = { "/addSavedRecipe/{userName}/", "/addSavedRecipe/{userName}" })
	public void AddSavedRcp(@PathVariable("userName") String userName, @RequestParam("RecipeName") String RecipeName) {
		Boolean answer;
		try {
			answer = service.addToSavedRecipes(userName, RecipeName);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = { "/deleteSavedRecipe/{userName}/", "/deleteSavedRecipe/{userName}" })
	public void DeleteSavedRcp(@PathVariable("userName") String userName,
			@RequestParam("RecipeName") String RecipeName) {
		Boolean answer;
		try {
			answer = service.deleteSavedRecipe(userName, RecipeName);
		} catch (InvalidInputException e) {
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

			@PathVariable("username") String username, @RequestParam("recipe") String recipeName,
			@RequestParam("amount") int amount) throws InvalidInputException {

		Meal m = service.logMeal(username, recipeName, amount);

		return convertToDto(m);
	}

	@GetMapping(value = { "reccomendMeals/{username}" })

	public RecipeDto recommendRecipe(@PathVariable("username") String username) throws InvalidInputException {

		Recipe r = service.recommendRecipe(username);

		return convertToDto(r);
	}

	private MealDto convertToDto(Meal m) {

		return modelMapper.map(m, MealDto.class);
	}

	private RecipeDto convertToDto(Recipe r) {

		return modelMapper.map(r, RecipeDto.class);
	}

}
