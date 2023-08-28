import { ref } from 'vue'

const useDarkmode = () => {
	const currentTheme = ref(localStorage.getItem('darkmode') || 'light')
	const updateTheme = () => document.documentElement.setAttribute('data-theme', currentTheme.value)
	updateTheme()

	const toggle = () => {
		if (currentTheme.value == 'light') {
			currentTheme.value = 'dark'
			updateTheme()
		} else {
			currentTheme.value = 'light'
			updateTheme()
		}
		localStorage.setItem('darkmode', currentTheme.value)
	}

	return { toggle, currentTheme }
}

export default useDarkmode
