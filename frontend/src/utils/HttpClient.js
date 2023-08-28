const request = async (base, route, method, headers, body, onRequest, onResponse) => {
	// FIXME TOO MANY PARAMS
	try {
		const url = route.substring(0,1) === '/' ? base + route : base + '/' + route
		const init = { method, headers, body }
		let req = new Request(url, init)
		onRequest.forEach(f => req = f(req) || req)
		let res = await fetch(req)
		onResponse.forEach(f => res = f(req, res) || res)
		if (!res.ok) {
			const error = new Error("API response not OK")
			error.res = res
			return Promise.reject(error)
		}
		return res
	} catch (err) {
		const error = new Error("HttpClient error")
		error.err = err
		return Promise.reject(error)
	}
}

const createHandler = (config) => {
	const defaultConfig = {
		baseRoute: '',
		headers: {},
		onRequest: [],
		onResponse: [],
	}
	config = {
		...defaultConfig,
		...config,
	}
	return {
		GET: async (route) =>
			request(config.baseRoute, route, 'GET', config.headers, null, config.onRequest, config.onResponse),
		POST: async (route, body) =>
			request(config.baseRoute, route, 'POST', config.headers, JSON.stringify(body), config.onRequest, config.onResponse)
	}
}

export default createHandler
