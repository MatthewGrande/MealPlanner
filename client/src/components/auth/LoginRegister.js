import React, { useReducer, useState, useMemo } from 'react';
import { Redirect, useLocation } from 'react-router-dom';
import { Flex, Input, Box } from '@chakra-ui/core';
import styled from '@emotion/styled';

import loginRegisterReducer, {
	changeInput,
	createInitialState,
	setErrorMessage,
	changeIsLogin,
} from './reducer';
import { Title, Text } from '../shared/TextComponents';
import { RoundedButton } from '../shared/Buttons';
import Logo from '../../images/Meal_Planner_Logo-no-bg.png';
import { register } from '../../api';

const FormInput = styled(Input)`
	border-radius: 10px;
	margin: 10px 0;
	width: 350px;
`;

const ImageWrapper = styled(Box)`
	width: 400px;
	height: 400px;
	margin: -40px 0;
`;

function LoginRegister() {
	const location = useLocation();
	const [redirect, setRedirect] = useState(false);
	const initialIsLogin = location.state ? location.state.isLogin : false;
	const [
		{ name, email, password, secondPassword, errorMessage, isLogin },
		dispatch,
	] = useReducer(
		loginRegisterReducer,
		createInitialState({
			initialEmail: '',
			initialIsLogin,
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

	async function handleSubmit(event) {
		event.preventDefault();

		if (!formValid()) {
			return;
		}

		if (!isLogin) {
			// Defaulting calorie goal to 2000
			const response = await register(email, password, 2000);
			if (response.ok) {
				const user = await response.json();
				setRedirect(true);
			}
		} else {
			// TODO: handle login here
		}
	}

	const submitButtonString = useMemo(() => (isLogin ? 'Log in' : 'Register'), [
		isLogin,
	]);

	if (redirect) {
		return <Redirect to="/MealLog"></Redirect>;
	}

	return (
		<Flex
			style={{ minHeight: '100vh' }}
			direction="column"
			align="center"
			justify="center"
			background="teal"
		>
			<ImageWrapper>
				<img src={Logo} />
			</ImageWrapper>
			<Text color="white" fontSize="xl" mb="2">
				{!isLogin && `You're just a step away from crushing your diet goals`}
				{isLogin && `Welcome back!`}
			</Text>
			{errorMessage && <Title>{errorMessage}</Title>}
			<form onSubmit={handleSubmit}>
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
				<Flex align="center">
					<RoundedButton
						mt="2"
						mb="2"
						bg="white"
						width="200"
						disabled={!formValid()}
						type="submit"
					>
						{submitButtonString}
					</RoundedButton>
				</Flex>
			</form>
			<Flex
				style={{ cursor: 'pointer' }}
				onClick={() => dispatch(changeIsLogin())}
			>
				<Text color="white">
					{`Click here to ${isLogin ? 'register' : 'login'}`}
				</Text>
			</Flex>
		</Flex>
	);
}

export default LoginRegister;
