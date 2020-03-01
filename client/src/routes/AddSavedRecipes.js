import React from 'react';
import { Flex, Heading, FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input, Box, Button,Text, Stack,NumberInput,
  NumberInputField,
  NumberInputStepper,
  NumberIncrementStepper,
  NumberDecrementStepper, Image} from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column" align="left">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading textAlign= "left">Search for a recipe:</Heading>
			<Stack shouldWrapChildren isInline spacing={30}>
				<FormControl>
				  <br></br>
				  <Input placeholder="Enter recipe" />
				  <br></br>
				  <Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width = "100px"
						>
						Search
					</Button>
					<br></br><br></br><br></br>
				<Heading textAlign= "left">List results:</Heading>
				##Backend needed
				<br></br>
				<br></br>
				 <Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width = "100px"
						>
						<a href="/ViewSavedRecipes">Cancel</a>
					</Button>
				<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width = "100px"
						>
						<a href="/ViewSavedRecipes">Save</a>
					</Button>


				</FormControl>			
			</Stack>
			</Flex>
		
	);
}

export default MealLog;
