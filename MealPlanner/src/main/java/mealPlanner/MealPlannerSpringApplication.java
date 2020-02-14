package mealPlanner;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import mealPlanner.controller.configuration.WebFrontendProperties;
import mealPlanner.model.MealManager;
import mealPlanner.persistence.PersistenceXStream;

@SpringBootApplication
public class MealPlannerSpringApplication extends SpringBootServletInitializer {

	@Autowired
	private WebFrontendProperties webFrontendProperties;

	public static void main(String[] args) {
		SpringApplication.run(MealPlannerSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		// Let the model matcher map corresponding fields by name
		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.NONE)
				.setDestinationNamingConvention(NamingConventions.NONE);
		return modelMapper;
	}

	@Bean
	public MealManager mealMan() {
		return PersistenceXStream.initializeModelManager(PersistenceXStream.getFilename());

	}

	// TODO add a Bean to provide a registration manager
	// @Bean
	// public RegistrationManager regMan() {
	// return
	// PersistenceXStream.initializeModelManager(PersistenceXStream.getFilename());
	// return null;
	// }

	// Enable CORS globally

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String frontendUrl = "http://" + webFrontendProperties.getIp() + ":" + webFrontendProperties.getPort();
				// For debug purposes, allow connecting from localhost as well
				registry.addMapping("/**").allowedOrigins(frontendUrl, "http://localhost:8087", "http://127.0.0.1:8087");
			}
		};

	}
}
