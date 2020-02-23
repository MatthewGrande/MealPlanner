import React from 'react';
import {
	Flex,
	Heading,
	FormControl,
	FormLabel,
	FormErrorMessage,
	FormHelperText,
	Input,
	Box,
	Button,
	Text,
} from '@chakra-ui/core';
import { Avatar, AvatarBadge, Stack } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
				<Heading>Diet Information</Heading>
				<Heading as="h3" size="l"></Heading>
				<br></br>
				<Stack>
					<FormControl>
						<FormLabel htmlFor="dietRestrictions">Diet Restrictions:</FormLabel>
						<Input
							id="dietRestrictions"
							placeholder="Eg. vegetarian, vegan, keto, etc. "
							width="500px"
						/>
					</FormControl>
					<br></br>
					<FormControl>
						<FormLabel htmlFor="Diet Length">Diet Length:</FormLabel>
						<Input
							id="dietRestrictions"
							placeholder="Eg. one week, one month, etc."
							width="500px"
						/>
					</FormControl>
					<br></br>
					<FormControl>
						<FormLabel htmlFor="Diet Goals">Diet Goals:</FormLabel>
						<Input
							id="dietGoals"
							placeholder="Eg. eat healthy, lose weight, etc. "
							width="500px"
						/>
					</FormControl>
					<br></br>
					<FormControl>
						<FormLabel htmlFor="Daily Calorie Intake">
							Daily Calorie Intake:
						</FormLabel>
						<Input
							id="dailyCalorieIntake"
							placeholder="1000 calories, etc. "
							width="500px"
						/>
					</FormControl>
				</Stack>
				<br></br>
				<br></br>
				<Stack shouldWrapChildren isInline spacing={30}>
					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="300px"
					>
						Cancel
					</Button>
					<br></br>

					<Button
						background="white"
						variantColor="teal"
						variant="outline"
						size="md"
						width="300px"
					>
						Save
					</Button>
				</Stack>
			</Flex>
		</Flex>
	);
}

export default MealLog;
