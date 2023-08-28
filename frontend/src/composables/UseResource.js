import { ref, isRef, unref, watchEffect } from 'vue'
import api from '@/utils/ApiClient.js'

const useResource = (path) => {
	const resource = ref(null)
	const pending = ref(true)
	const error = ref(null)

	const sendRequest = async (fetcher) => {
		pending.value = true
		try {
			const response = await fetcher(unref(path))
			resource.value = response.data
			error.value = null
		} catch (e) {
			resource.value = null
			if (e.response) {
				try {
					error.value = new Error('Oops! Error response: ' + e.response.data.message)
				} catch {
					error.value = new Error('Oops! There was an error response')
				}
			} else if (e.request) {
				error.value = new Error('Oops! There was no response')
			} else {
				error.value = new Error('Oops! ' + e.message)
			}
		}
		pending.value = false
	}

	const load = () => sendRequest(api.get)
	const create = (data) => sendRequest((p) => api.post(p, data))
	const update = (data) => sendRequest((p) => api.put(p, data))
	const remove = () => sendRequest(api.delete)

	if (isRef(path)) {
		watchEffect(load)
	} else {
		load()
	}

	return { resource, pending, error, load, create, update, remove }
}

export default useResource
