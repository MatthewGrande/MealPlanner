import React from 'react';
import { Flex, Heading } from '@chakra-ui/core';
import { Avatar, Stack } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
				<br></br>
				<Stack isInline>
					<Avatar size="2xl" src="https://bit.ly/broken-link" />
				</Stack>
				<br></br>
				<Heading>Client Name</Heading>
				<Heading as="h3" size="l"></Heading>
				<br></br>
				<h3>
					<b>Calorie Goal: </b> ##Pull from backend##
				</h3>
				<br></br>
				<h3>
					<b>Current Daily Calorie Count: </b> ##Pull from backend##
				</h3>
				<br></br>
				<h3>
					<b>Progress so far: </b> ##Pull from backend##
				</h3>
				<br></br>
				<h3>
					<b>Keep up the good work!!</b>
				</h3>
			</Flex>
		</Flex>
	);
}

export default MealLog;
