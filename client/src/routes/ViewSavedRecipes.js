import React from 'react';
import { Flex, Heading, FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input, Box, Button,Text, Stack,NumberInput,
  NumberInputField,
  NumberInputStepper,
  NumberIncrementStepper,
  NumberDecrementStepper, Image } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column" align="center">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading textAlign= "center">Saved Recipes</Heading>
			<Stack shouldWrapChildren isInline spacing={30}>
				<FormControl>
				  <br></br>
				  <Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width = "300px"
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
							width = "300px"
						>
						Add Saved Recipe
						</Button>
				</FormControl>			
			</Stack>
			<br></br>
			</Flex>
		
	);
}

export default MealLog;
