import React from 'react';
import { Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import { ThemeProvider } from '@chakra-ui/core';
import { render, cleanup } from '@testing-library/react';
import AccountSettings from './AccountSettings';

describe('Account Settings Tests', () => {
	afterAll(() => {
		cleanup();
	});

	it('renders the users weekly overview and stats', async () => {
		const history = createMemoryHistory();
		const { getByPlaceholderText } = render(
			<ThemeProvider>
				<Router history={history}>
					<AccountSettings />
				</Router>
			</ThemeProvider>
		);
	});
});
