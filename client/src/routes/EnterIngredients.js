import React from 'react';
import {
	Flex,
	Heading,
	FormControl,
	Stack,
	FormLabel,
	FormErrorMessage,
	FormHelperText,
	Input,
	Box,
	Button,
	Text,
	NumberInput,
	NumberInputField,
	NumberInputStepper,
	NumberIncrementStepper,
	NumberDecrementStepper,
} from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function EnterIngredients() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
				<Heading>Input Ingredients</Heading>
				<br></br>
				<Heading as="h2" size="l">
					Current Ingredients
				</Heading>
				<Heading as="h3" size="l">
					##NEED BACKEND TO LIST CURRENT INGREDIENTS##
				</Heading>
				<br></br>
				<Stack shouldWrapChildren isInline spacing={30}>
					<FormControl>
						<FormLabel htmlFor="ingredients">Ingredient:</FormLabel>
						<Input id="ingredients" placeholder="Ingredient" />
					</FormControl>
					<FormControl>
						<FormLabel htmlFor="ingredients">Quantity</FormLabel>
						<NumberInput>
							<NumberInputField />
							<NumberInputStepper>
								<NumberIncrementStepper />
								<NumberDecrementStepper />
							</NumberInputStepper>
						</NumberInput>
					</FormControl>
				</Stack>
				<br></br>
				<Button
					background="white"
					variantColor="teal"
					variant="outline"
					size="md"
					width="200px"
				>
					Add ingredient
				</Button>
				<br></br>
				<Button
					background="white"
					variantColor="teal"
					variant="outline"
					size="md"
					width="300px"
				>
					Search Recipes Using These Ingredients
				</Button>
			</Flex>
		</Flex>
	);
}

export default EnterIngredients;
