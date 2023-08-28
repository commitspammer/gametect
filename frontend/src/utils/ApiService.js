import createHandler from '@/utils/HttpClient.js'
import eventBus from '@/utils/EventBus.js'

const api = createHandler({
	baseRoute: 'http://localhost:3000',
	headers: {
		'Content-Type': 'application/json',
		'Accept': 'application/json',
	},
	onRequest: [
		req => {
			if (!localStorage.getItem('token')) return req
			const newHeaders = new Headers(req.headers)
			newHeaders.set('Authorization', 'Bearer ' + localStorage.getItem('token'))
			return new Request(req, { headers: newHeaders })
		},
	],
	onResponse: [
		(req, res) => {
			if (req.url === 'http://localhost:3000/login' && res.ok) {
				res.clone().json()
					.then(j => localStorage.setItem('token', j.token))
			}
			if (req.url === 'http://localhost:3000/signup' && res.ok) {
				res.clone().json()
					.then(j => localStorage.setItem('token', j.token))
			}
		},
		(req, res) => {
			if (req.url !== 'http://localhost:3000/login' && res.status === 401) {
				localStorage.removeItem('token')
				const fallback = createHandler({
					baseRoute: 'http://localhost:3000',
					headers: {
						'Content-Type': 'application/json',
						'Accept': 'application/json',
					},
				})
				fallback.POST('/refresh', { token: localStorage.getItem('token') })
					.then(res => res.clone().json())
					.then(j => localStorage.setItem('token', j.token))
					.catch(() => eventBus.emit('token-expired'))
					// TODO add 'maxRetries' config value
					// TODO or add a 'retry' argument to retry request
			}
		},
	],
})

export default api
