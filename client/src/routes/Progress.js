import React, { useEffect, useState } from 'react';
import {
	Flex,
	Heading,
	CircularProgress,
	CircularProgressLabel,
	Text,
} from '@chakra-ui/core';

import { getProgress } from '../api';
import LoggedInNavbar from '../components/LoggedInNavbar';

function MealLog() {
	// TODO: pull username from
	const username = 'max@maxmusing.com';
	const [progress, setProgress] = useState(null);

	useEffect(() => {
		if (!username) {
			return;
		}

		getProgress(username).then(fetchedProgress => setProgress(fetchedProgress));
	}, [username]);

	return (
		<Flex width="100%" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			<Flex direction="column" m="3rem">
				<Heading mb="2rem">Progress</Heading>
				{progress && (
					<>
						<Text mb="2rem" fontSize="2xl">
							You've consumed {Math.round(progress * 100)}% of your daily
							calorie goal for the day.
						</Text>
						<CircularProgress value={progress * 100} size="20rem" capIsRound>
							<CircularProgressLabel>
								{Math.round(progress * 100)}%
							</CircularProgressLabel>
						</CircularProgress>
					</>
				)}
			</Flex>
		</Flex>
	);
}

export default MealLog;
