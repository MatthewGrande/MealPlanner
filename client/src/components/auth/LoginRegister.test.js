import React from 'react';
import { Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import { ThemeProvider } from '@chakra-ui/core';
import { render, cleanup } from '@testing-library/react';
import LoginRegister from './LoginRegister';

describe('Login Register Tests', () => {
	afterAll(() => {
		cleanup();
	});

	it('renders the users weekly overview and stats', async () => {
		const history = createMemoryHistory();
		const { getByPlaceholderText } = render(
			<ThemeProvider>
				<Router history={history}>
					<LoginRegister />
				</Router>
			</ThemeProvider>
		);

		const nameInput = getByPlaceholderText('Full Name');
		expect(nameInput).toBeDefined();

		const emailInput = getByPlaceholderText('Email');
		expect(emailInput).toBeDefined();

		const passwordInput = getByPlaceholderText('Password');
		expect(passwordInput).toBeDefined();

		const secondPasswordInput = getByPlaceholderText('Re-enter password');
		expect(secondPasswordInput).toBeDefined();
	});
});
