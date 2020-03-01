import React from 'react';
import { Router } from 'react-router-dom';
import { createMemoryHistory } from 'history';
import { ThemeProvider } from '@chakra-ui/core';
import { render, cleanup } from '@testing-library/react';
import MealLog from './AddSavedRecipes';

describe('Add Saved Recipes Tests', () => {
	afterAll(() => {
		cleanup();
	});

	it('renders the users weekly overview and stats', async () => {
		const history = createMemoryHistory();
		const { getByPlaceholderText } = render(
			<ThemeProvider>
				<Router history={history}>
					<MealLog />
				</Router>
			</ThemeProvider>
		);

		const enterRecipeInput = getByPlaceholderText('Enter recipe');
		expect(enterRecipeInput).toBeDefined();
	});
});
