import { isRef, watch } from 'vue'

const useHistory = () => {
	const track = (src, delay) => {
		const state = [isRef(src) ? src.value : src] //what if i'm not ref(''), but reactive({})?

		const startWatch = () => 
			watch(src, (newValue, oldValue, onCleanup) => {
				if (oldValue == newValue) return
				console.log(state)
				const timeout = setTimeout(() => state.push(newValue), delay || 1000)
				onCleanup(() => clearTimeout(timeout))
			})

		let stopWatch = startWatch()
		
		const revert = () => {
			stopWatch()
			if (state.length > 1) state.pop()
			if (isRef(src))
				src.value = state[state.length - 1]
			else
				src = state[state.length - 1]
			stopWatch = startWatch()
		}

		const reset = () => {
			stopWatch()
			while (state.length > 1) state.pop()
			if (isRef(src))
				src.value = state[state.length - 1]
			else
				src = state[state.length - 1]
			stopWatch = startWatch()
		}
		
		return { revert, reset }
	}

	return { track }
}

export default useHistory
