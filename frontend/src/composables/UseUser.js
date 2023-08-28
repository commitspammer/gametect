import { ref, computed } from 'vue'
import api from '@/utils/ApiService.js'

const user = ref(null)
const pending = ref(true)
const error = ref(null)
const loggedIn = ref(localStorage.getItem('logged_in') === 'true')

const useUser = () => {
	const login = async (email, password) => {
		pending.value = true
		try {
			const res = await api.POST('/auth/login', { email: email, password: password })
			const data = await res.json()
			user.value = data.user
			error.value = null
			localStorage.setItem('uid', data.user.id)
			localStorage.setItem('logged_in', true)
			loggedIn.value = true;
		} catch (e) {
			user.value = null
			if (e.res) {
				e.res.json()
					.then(data => error.value = new Error('Oops! ' + data.message))
					.catch(() => error.value = new Error('Oops!!!'))
			} else if (e.err) {
				error.value = e.err
			} else {
				error.value = e
			}
		}
		pending.value = false
	}

	const register = async (name, email, password) => {
		pending.value = true;
		try {
			const res = await api.POST('/auth/register', { name: name, email: email, password: password })
			const data = await res.json()
			user.value = data.user
			error.value = null
			localStorage.setItem('uid', data.user.id)
			localStorage.setItem('logged_in', true)
			loggedIn.value = true;
		} catch (e) {
			user.value = null
			if (e.res) {
				e.res.json()
					.then(data => error.value = new Error('Oops! ' + data.message))
					.catch(() => error.value = new Error('Oops!!!'))
			} else if (e.err) {
				error.value = e.err
			} else {
				error.value = e
			}
		}
		pending.value = false
	}

	const logout = () => {
		pending.value = true
		user.value = null
		error.value = null
		localStorage.removeItem('uid')
		localStorage.setItem('logged_in', false)
		loggedIn.value = false;
		pending.value = false
	}

	const reload = async () => {
		pending.value = true
		try {
			const res = await api.GET('/users/' + localStorage.getItem('uid'))
			const data = await res.json()
			user.value = data
			error.value = null
		} catch (e) {
			user.value = null
			if (e.res) { // this is here so a failed fetch (offline api) doesnt log user out
				// FIXME navbar will still break, since it checks user, not logged_in
				error.value = null
				localStorage.removeItem('uid')
				// FIXME mass-refreshing breaks pending fetches! this line makes it worse by logging you out!
				// FIXME delaying App.vue's reload() call kinda fixes it...
				localStorage.setItem('logged_in', false)
				loggedIn.value = false;
			} else if (e.err) {
				error.value = e.err
			} else {
				error.value = e
			}
		}
		pending.value = false
	}

	const userId = computed(() => user.value ? user.value.id : null)

	return { user, pending, error, userId, loggedIn, login, register, logout, reload }
}

export default useUser
