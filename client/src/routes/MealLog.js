import React from 'react';
import { Flex, Heading, FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input, Box, Button,Text } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading>Meal Log</Heading>
			<Heading as="h3" size="l">
   			 ##NEED BACKEND TO LIST MEALS##
  			</Heading>
  			<br></br>
  			<Heading>Add a Meal</Heading>
  			<FormControl>
			  <FormLabel htmlFor="mealName">Meal Name</FormLabel>
			  <Input id="mealName" placeholder="Meal Name" />
			  <FormLabel htmlFor="quantity">Quantity</FormLabel>
			  <Input id="quantity" placeholder="Quantity" />
			</FormControl>
			<br></br>
			<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Add a Meal
						</Button>
		</Flex>
	);
}

export default MealLog;
