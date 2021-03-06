import React from 'react';
import { Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import { ThemeProvider } from '@chakra-ui/core';
import { render, cleanup } from '@testing-library/react';
import DietInformation from './DietInformation';

describe('Diet Information Tests', () => {
	afterAll(() => {
		cleanup();
	});

	it('renders the users weekly overview and stats', async () => {
		const history = createMemoryHistory();
		const { getByPlaceholderText } = render(
			<ThemeProvider>
				<Router history={history}>
					<DietInformation />
				</Router>
			</ThemeProvider>
		);

		const enterRecipeInput = getByPlaceholderText(
			'Eg. one week, one month, etc.'
		);
		expect(enterRecipeInput).toBeDefined();
	});
});
