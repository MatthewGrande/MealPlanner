package mealPlanner.controller;
 
@RestController 

public class MealPlannerRestController {

	@Autowired private MealPlannerService service;
	@Autowired private ModelMapper modelMapper;

	@PostMapping(value = { "/newUser/{username}/{password}/{calorieGoal}" })
	
	public UserDto createUser(
			@PathVariable("username") String username,
			@PathVariable("password") String password,
			@PathVariable("calorieGoal") int calorieGoal) throws InvalidInputException {
		
		User u = service.createUser(username, password, calorieGoal)
		return convertToDto(u); 
	}
	
	private UserDto convertToDto(User u) {

		return modelMapper.map(u, UserDto.class);
	}

}