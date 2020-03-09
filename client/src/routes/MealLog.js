import React from 'react';
import {
	Flex,
	Heading,
	FormControl,
	FormLabel,
	Input,
	Button,
} from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
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
					width="300px"
				>
					Add a Meal
				</Button>
			</Flex>
		</Flex>
	);
}

export default MealLog;
