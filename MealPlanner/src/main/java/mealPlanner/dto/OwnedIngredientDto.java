package mealPlanner.dto;

public class OwnedIngredientDto {
	String username;
	String ingredientName;
	int amount;
	
	public OwnedIngredientDto() {
	}

	public OwnedIngredientDto(String username, String ingredientName, int amount) {

		this.username = username;
		this.ingredientName = ingredientName;
		this.amount = amount;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getIngredient() {
		return this.ingredientName;
	}
	
	public int getAmount() {
		return this.amount;
	}
}
