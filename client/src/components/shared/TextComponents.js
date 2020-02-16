import React from 'react';
import { Heading, Text as ChakraText } from '@chakra-ui/core';
import styled from '@emotion/styled';

export function Title({ children, className, style, ...otherProps }) {
	return (
		<Heading className={className} style={style} {...otherProps}>
			{children}
		</Heading>
	);
}

export function Text({ className, style, children, ...otherProps }) {
	return (
		<ChakraText className={className} style={style} {...otherProps}>
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

export function Subheader({ children, className, style, ...otherProps }) {
	return (
		<StyledSubheader className={className} style={style} {...otherProps}>
			{children}
		</StyledSubheader>
	);
}
