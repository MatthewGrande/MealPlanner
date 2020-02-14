import React from 'react';
import { Heading, Text as ChakraText } from '@chakra-ui/core';
import styled from '@emotion/styled';

export function Title({ children, className, style }) {
	return (
		<Heading className={className} style={style}>
			{children}
		</Heading>
	);
}

export function Text({ className, style, children }) {
	return (
		<ChakraText className={className} style={style}>
			{children}
		</ChakraText>
	);
}

const StyledSubheader = styled(Text)`
	font-family: Roboto;
	font-size: 1em;
	margin: 10px;
	color: ${props => props.theme.global.colors.brand};
	font-weight: bold;
`;

export function Subheader({ children, className, style }) {
	return (
		<StyledSubheader className={className} style={style}>
			{children}
		</StyledSubheader>
	);
}
