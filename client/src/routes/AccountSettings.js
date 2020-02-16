import React from 'react';
import { Flex, Heading, FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input, Box, Button,Text } from '@chakra-ui/core';
import { Avatar, AvatarBadge, Stack } from "@chakra-ui/core";

import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<br></br>
			<Heading>Account Settings</Heading>
			<Heading as="h3" size="l">
  			</Heading>
  			<br></br>
  			<FormControl>
			  <FormLabel htmlFor="profilePicture">Profile Picture</FormLabel>
			</FormControl>
			<br></br>
			<Stack isInline>
  				<Avatar
   				 size="2xl"
   				 src="https://bit.ly/broken-link"
  				/>
  			</Stack>
  			<br></br>
			<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width="300px"
						>
						Delete Account

						</Button>
			<br></br>
				
			<Button
							background="white"
							variantColor="teal"
							variant="outline"
							size="md"
							width="300px"
						>
						Change Password

						</Button>
		</Flex>
	);
}

export default MealLog;
