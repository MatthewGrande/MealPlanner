import React from 'react';
import { keyframes } from '@emotion/core';
import styled from '@emotion/styled';

const LoaderWrapper = styled.div`
	height: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
`;

const rotate = keyframes`
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
`;

const Loader = styled.div`
	border: solid #f3f3f3;
	border-top: solid ${props => props.theme.global.colors.brand};
	border-radius: 50%;
	width: ${({ size }) => size}px;
	height: ${({ size }) => size}px;
	animation: ${rotate} 2s linear infinite;
`;

export default function({ size = 20 }) {
	return (
		<LoaderWrapper data-testid="loader">
			<Loader size={size} />
		</LoaderWrapper>
	);
}
