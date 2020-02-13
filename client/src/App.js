import React from 'react';
import { CSSReset, ThemeProvider } from '@chakra-ui/core';

import SampleComponent from './components/SampleComponent';

function App() {
	return (
		<ThemeProvider>
			<CSSReset />
			<SampleComponent></SampleComponent>
		</ThemeProvider>
	);
}

export default App;
