import React from 'react';
import { Flex, Image } from '@chakra-ui/core';

import Navbar from '../components/Navbar';
import HeroImage from '../images/hero-image.jpg';

function LandingPage() {
	return (
		<Flex width="100%" height="100vh" direction="column">
			<Navbar></Navbar>
			<Image flexGrow="1" objectFit="cover" src={HeroImage}></Image>
		</Flex>
	);
}

export default LandingPage;
