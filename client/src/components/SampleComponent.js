import React from 'react';
import { Box, Image, Flex, Badge, Text } from '@chakra-ui/core';

function SampleComponent() {
	return (
		<Box width={400} m={50}>
			<Text mb={16}>This is a sample component using the Chakra library</Text>
			<Image rounded="md" src="https://bit.ly/2k1H1t6" />
			<Flex align="baseline" mt={2}>
				<Badge variantColor="pink">Plus</Badge>
				<Text
					ml={2}
					textTransform="uppercase"
					fontSize="sm"
					fontWeight="bold"
					color="pink.800"
				>
					Verified &bull; Cape Town
				</Text>
			</Flex>
			<Text mt={2} fontSize="xl" fontWeight="semibold" lineHeight="short">
				Modern, Chic Penthouse with Mountain, City & Sea Views
			</Text>
			<Text mt={2}>$119/night</Text>
			<Flex mt={2} align="center">
				<Text ml={1} fontsize="sm">
					<b>4.84</b> (190)
				</Text>
			</Flex>
		</Box>
	);
}

export default SampleComponent;
