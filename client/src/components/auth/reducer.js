const CHANGE_INPUT = 'CHANGE_INPUT';
const CHANGE_IS_LOGIN = 'CHANGE_IS_LOGIN';
const SET_ERROR_MESSAGE = 'SET_ERROR_MESSAGE';

export const createInitialState = ({
	initialIsLogin = false,
	initialEmail = '',
}) => ({
	name: '',
	email: initialEmail,
	password: '',
	secondPassword: '',
	errorMessage: null,
	isLogin: initialIsLogin,
});

export default function reducer(state, action) {
	switch (action.type) {
		case CHANGE_INPUT:
			return {
				...state,
				[action.input]: action.value,
			};
		case CHANGE_IS_LOGIN:
			return {
				...createInitialState({ initialIsLogin: !state.isLogin }),
			};
		case SET_ERROR_MESSAGE:
			return {
				...state,
				errorMessage: action.payload,
			};
		default:
			return state;
	}
}

export function changeInput(input, value) {
	return {
		type: CHANGE_INPUT,
		input,
		value,
	};
}

export function changeIsLogin() {
	return {
		type: CHANGE_IS_LOGIN,
	};
}

export function setErrorMessage(message) {
	return {
		type: SET_ERROR_MESSAGE,
		payload: message,
	};
}
