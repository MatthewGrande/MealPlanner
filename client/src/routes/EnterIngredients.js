import React from 'react';
import { Flex, Heading,  FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input, Box, Button,Text } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function EnterIngredients() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading>Input Ingredients</Heading>
			<br></br>
			<FormControl>
			  <FormLabel htmlFor="ingredients">Enter desired ingredients separated by a comma:</FormLabel>
			  <Input id="ingredients" placeholder="Ingredients" />
			</FormControl>
			<br></br>
			<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Search Recipes
						</Button>
		</Flex>
	);
}

export default EnterIngredients;
