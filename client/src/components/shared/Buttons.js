import React from 'react';
import styled from '@emotion/styled';
import { Button } from '@chakra-ui/core';

import Loader from './Loader';

const StyledRoundedButton = styled(Button)`
	width: ${props => props.width}px;
	border-radius: 20px;
	padding: 10px;
	text-align: center;
	margin-top: 12px;
	font-family: Roboto;
`;

export function RoundedButton({
	className,
	style,
	loading,
	label,
	inverted = false,
	width,
	...otherProps
}) {
	return (
		<StyledRoundedButton
			inverted={inverted}
			style={style}
			width={width}
			className={className}
			label={loading ? <Loader size={20} /> : label}
			{...otherProps}
		/>
	);
}
