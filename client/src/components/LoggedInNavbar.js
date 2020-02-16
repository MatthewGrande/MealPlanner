import React from 'react';
import { Link } from 'react-router-dom';
import { Box, Button, Flex, Heading, Image, Text } from '@chakra-ui/core';

import Logo from '../images/logo.png';

function Navbar() {
	return (
		<Flex
			height="4rem"
			background="teal"
			color="white"
			justify="space-between"
			flexShrink="0"
			px="2rem"
		>
			<Flex align="center">
				<Image src={Logo} size="3rem" mr="1rem"></Image>
				<Heading fontSize="1.5rem">Meal Planner</Heading>
				<Text ml="1rem">an app by Team Refreshment</Text>
			</Flex>
			<Flex align="center">
				<Box mr="1rem">
					<Link to="/home">
						<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Home
						</Button>
					</Link>
				</Box>
				<Box mr="1rem">
					<Link to="/progress">
						<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Progress
						</Button>
					</Link>
				</Box>
				<Box mr="1rem">
					<Link to="/mealLog">
						<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Meal Log
						</Button>
					</Link>
				</Box>
				<Box mr="1rem">
					<Link to="/account">
						<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
						>
							Account
						</Button>
					</Link>
				</Box>
			</Flex>
		</Flex>
	);
}

export default Navbar;
