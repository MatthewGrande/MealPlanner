import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { CSSReset, ThemeProvider } from '@chakra-ui/core';

import LandingPage from './routes/LandingPage';
import LoginRegister from './components/auth/LoginRegister';
import Progress from './routes/Progress';
import MealLog from './routes/MealLog';
import EnterIngredients from './routes/EnterIngredients';
import AccountSettings from './routes/AccountSettings';
import DietInformation from './routes/DietInformation';
import MealSuggestions from './routes/MealSuggestions';
import ViewSavedRecipes from './routes/ViewSavedRecipes';
import Profile from './routes/Profile';


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
					<Route exact path="/progress">
						<Progress></Progress>
					</Route>
					<Route exact path="/MealLog">
						<MealLog></MealLog>
					</Route>
					<Route exact path="/EnterIngredients">
						<EnterIngredients></EnterIngredients>
					</Route>
					<Route exact path="/AccountSettings">
						<AccountSettings></AccountSettings>
					</Route>
					<Route exact path="/DietInformation">
						<DietInformation></DietInformation>
					</Route>
					<Route exact path="/MealSuggestions">
						<MealSuggestions></MealSuggestions>
					</Route>
					<Route exact path="/ViewSavedRecipes">
						<ViewSavedRecipes></ViewSavedRecipes>
					</Route>
					<Route exact path="/Profile">
						<Profile></Profile>
					</Route>
				</Switch>
			</Router>
		</ThemeProvider>
	);
}

export default App;
