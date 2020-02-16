import React from 'react';
import { Flex, Image } from '@chakra-ui/core';

import Navbar from '../components/Navbar';
import HeroImage from '../images/hero-image.jpg';

function EnterIngredients() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<Navbar></Navbar>
			
		</Flex>
	);
}

export default EnterIngredients;
