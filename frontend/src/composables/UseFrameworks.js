import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useFrameworks = () => {
	const path = computed(() => '/frameworks')

	const fields = useResource(path)

	return { ...fields, frameworks: fields.resource }
}

export default useFrameworks
