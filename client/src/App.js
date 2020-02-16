import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { CSSReset, ThemeProvider } from '@chakra-ui/core';

import LandingPage from './routes/LandingPage';
import LoginRegister from './components/auth/LoginRegister';
import MealLog from './routes/MealLog';
import EnterIngredients from './routes/EnterIngredients';

function App() {
	return (
		<ThemeProvider>
			<CSSReset />
			<Router>
				<Switch>
					{/* Add new pages here as a Route */}
					<Route exact path="/">
						<LandingPage />
					</Route>
					<Route exact path="/auth">
						<LoginRegister />
					</Route>
					<Route exact path="/MealLog">
						<MealLog></MealLog>
					</Route>
					<Route exact path="/EnterIngredients">
						<EnterIngredients></EnterIngredients>
					</Route>
				</Switch>
			</Router>
		</ThemeProvider>
	);
}

export default App;
