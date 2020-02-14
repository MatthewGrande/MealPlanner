import React, { useReducer, useMemo } from 'react';
import { Flex, Input } from '@chakra-ui/core';
import styled from '@emotion/styled';

import loginRegisterReducer, {
	changeInput,
	createInitialState,
	setErrorMessage,
	changeIsLogin,
} from './reducer';
import { Title, Subheader, Text } from '../shared/TextComponents';

const FormInput = styled(Input)`
	border-radius: 10px;
	margin: 10px 0;
	width: 400px;
	color: red;
`;

function LoginRegister() {
	const [
		{ name, email, password, secondPassword, errorMessage, isLogin },
		dispatch,
	] = useReducer(
		loginRegisterReducer,
		createInitialState({
			initialEmail: '',
			initialIsLogin: false,
		})
	);

	function handleInputChange(event) {
		dispatch(changeInput(event.target.name, event.target.value));
	}

	function formValid() {
		return (
			(!isLogin &&
				email.length !== 0 &&
				name.length !== 0 &&
				password.length !== 0 &&
				secondPassword.length !== 0 &&
				password.length === secondPassword.length) ||
			(isLogin && email.length !== 0 && password.length !== 0)
		);
	}

	const submitButtonString = useMemo(() => (isLogin ? 'Log in' : 'Register'), [
		isLogin,
	]);

	return (
		<Flex
			style={{ minHeight: '100vh' }}
			direction="column"
			align="center"
			justify="center"
		>
			<Title>{`${isLogin ? 'Log in' : 'Create an Account'}`}</Title>
			<Text style={{ marginTop: '-10px' }}>
				{!isLogin && `You're just a step away from fantasy domination`}
				{isLogin && `Welcome back!`}
			</Text>
			{errorMessage && <Title>{errorMessage}</Title>}
			<Flex direction="column">
				{!isLogin && (
					<FormInput
						name="name"
						placeholder="Full Name"
						value={name}
						onChange={handleInputChange}
					/>
				)}
				<FormInput
					name="email"
					placeholder="Email"
					value={email}
					onChange={handleInputChange}
				/>
				<FormInput
					name="password"
					placeholder="Password"
					value={password}
					type="password"
					onChange={handleInputChange}
				/>
				{!isLogin && (
					<FormInput
						name="secondPassword"
						placeholder="Re-enter password"
						value={secondPassword}
						type="password"
						onChange={handleInputChange}
					/>
				)}
			</Flex>
			<Flex
				style={{ cursor: 'pointer' }}
				onClick={() => dispatch(changeIsLogin())}
			>
				<Text style={{ opacity: '0.5' }}>
					{`Click here to ${isLogin ? 'register' : 'login'}`}
				</Text>
			</Flex>
		</Flex>
	);
}

export default LoginRegister;
