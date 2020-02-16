package mealPlanner.dto;

public class UserDto {

	String username;
	String password;


	public UserDto() {
	}

	public UserDto(String username, String password) {

		this.username = username;
		this.password = password;		
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}	
	
}
