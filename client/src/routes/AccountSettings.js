import React, { useState } from 'react';
import { Redirect } from 'react-router-dom';
import { Flex, Heading, FormControl, FormLabel, Button } from '@chakra-ui/core';
import { Avatar, Stack } from '@chakra-ui/core';

import { deleteAccount } from '../api';
import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	const [deleteAccountLoading, setDeleteAccountLoading] = useState(false);
	const [redirect, setRedirect] = useState(false);

	// TODO: pull username and password from store
	const username = 'max@maxmusing.com';
	const password = 'password123';

	const handleDeleteAccount = async () => {
		setDeleteAccountLoading(true);
		await deleteAccount(username, password);
		setRedirect(true);
	};

	if (redirect) {
		return <Redirect to="/" />;
	}

	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
				<Heading>Account Settings</Heading>
				<Heading as="h3" size="l"></Heading>
				<br></br>
				<FormControl>
					<FormLabel htmlFor="profilePicture">Profile Picture</FormLabel>
				</FormControl>
				<br></br>
				<Stack isInline>
					<Avatar size="2xl" src="https://bit.ly/broken-link" />
				</Stack>
				<br></br>
				<Button
					background="white"
					variantColor="teal"
					variant="outline"
					size="md"
					width="300px"
					onClick={handleDeleteAccount}
					isLoading={deleteAccountLoading}
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
		</Flex>
	);
}

export default MealLog;
