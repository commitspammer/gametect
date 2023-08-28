const eventBus = {
	on(event, callback) {
		document.addEventListener(event, e => callback(e.detail))
	},
	emit(event, data) {
		document.dispatchEvent(new CustomEvent(event, { detail: data }))
	},
	discard(event, callback) {
		document.removeEventListener(event, callback)
	},
}

export default eventBus
