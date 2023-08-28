import axios from 'axios';
import eventBus from '@/utils/EventBus.js'

const api = axios.create({
	baseURL: 'http://localhost:3000'
});

api.interceptors.request.use((config) => {
	const token = localStorage.getItem('token');
	if (token) {
		config.headers.Authorization = `Bearer ${token}`;
	}
	return config;
}, (error) => {
	return Promise.reject(error);
});

api.interceptors.response.use((response) => {
	if (response.config.url === 'http://localhost:3000/login') {
		const token = response.data.token;
		if (token) {
			localStorage.setItem('token', token);
		}
	}
	return response;
}, (error) => {
	if (error.response && error.response.status === 401) {
		eventBus.emit('token-expired');
	}
	return Promise.reject(error);
});

export default api;
