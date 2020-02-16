import React from 'react';
import { Flex, Image } from '@chakra-ui/core';

import LoggedInNavbar from '../components/LoggedInNavbar';
import HeroImage from '../images/hero-image.jpg';

function MealLog() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<LoggedInNavbar></LoggedInNavbar>
			
		</Flex>
	);
}

export default MealLog;
