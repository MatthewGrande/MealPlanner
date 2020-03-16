import React from 'react';
import { Flex, Heading, FormControl, Button, Stack } from '@chakra-ui/core';

import { deleteSavedRecipe } from '../api';
import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	const handleDeleteSavedRecipe = () => {
		// TODO: update once front end has user and recipe data
		const username = 'max@maxmusing.com';
		const recipeName = 'Apple pie';
		deleteSavedRecipe(username, recipeName);
	};

	return (
		<Flex width="100%" height="100vh" direction="column" align="center">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading textAlign="center">Saved Recipes</Heading>
			<Stack shouldWrapChildren isInline spacing={30}>
				<FormControl>
					<br></br>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="200px"
						onClick={handleDeleteSavedRecipe}
					>
						Delete Saved Recipe
					</Button>
				</FormControl>
				<FormControl>
					<br></br>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="200px"
					>
						<a href="/AddSavedRecipes">Add Saved Recipe</a>
					</Button>
				</FormControl>
			</Stack>
			<br></br>
		</Flex>
	);
}

export default MealLog;
