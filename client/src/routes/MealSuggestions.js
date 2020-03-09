import React from 'react';
import {
	Flex,
	Heading,
	FormControl,
	Button,
	Stack,
	Image,
} from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';
import Breakfast from '../images/Breakfast.jpg';
import Lunch from '../images/Lunch.jpg';
import Dinner from '../images/Dinner.jpg';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column" align="center">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading textAlign="center">Meal Suggestions</Heading>
			<Stack shouldWrapChildren isInline spacing={30}>
				<FormControl>
					<h2>
						<b>
							<center>Breakfast</center>
						</b>
					</h2>
					<Image htmlWidth="300px" htmlHeight="200" src={Breakfast}></Image>
					<br></br>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="300px"
					>
						What's for Breakfast?
					</Button>
				</FormControl>
				<FormControl>
					<h2>
						<b>
							<center>Lunch</center>
						</b>
					</h2>
					<Image htmlWidth="300px" htmlHeight="350" src={Lunch}></Image>
					<br></br>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="300px"
					>
						What's for Lunch?
					</Button>
				</FormControl>
				<FormControl>
					<h2>
						<b>
							<center>Dinner</center>
						</b>
					</h2>
					<Image htmlWidth="300px" htmlHeight="200" src={Dinner}></Image>
					<br></br>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="300px"
					>
						What's for Dinner?
					</Button>
				</FormControl>
			</Stack>
			<br></br>
		</Flex>
	);
}

export default MealLog;
