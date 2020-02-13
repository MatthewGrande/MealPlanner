import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { CSSReset, ThemeProvider } from '@chakra-ui/core';

import LandingPage from './routes/LandingPage';

function App() {
	return (
		<ThemeProvider>
			<CSSReset />
			<Router>
				<Switch>
					{/* Add new pages here as a Route */}
					<Route exact path="/">
						<LandingPage></LandingPage>
					</Route>
				</Switch>
			</Router>
		</ThemeProvider>
	);
}

export default App;
