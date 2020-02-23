package mealPlanner.dto;

import java.util.List;

import mealPlanner.model.DietType;

public class RecipeDto {
	
	  private int calorieCountPerServing;
	  private String name;
	  private List<DietType> dietType;
	
	public RecipeDto() {
	}

	public RecipeDto(String name, int calorieCountPerServing, List<DietType> dietType) {

		this.name = name;
		this.calorieCountPerServing = calorieCountPerServing;
		this.dietType = dietType;

	}
	
	public String getRecipeName() {
		return this.name;
	}
	
	public int getCalorieCountPerServing() {
		return this.calorieCountPerServing;
	}
	
	public List<DietType> getDietType() {
		return this.dietType;
	}

}
