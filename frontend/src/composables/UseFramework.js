import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useFramework = (frameworkId) => {
	const path = computed(() => frameworkId ? '/frameworks/' + unref(frameworkId) : '/framework')

	const fields = useResource(path)

	return { ...fields, framework: fields.resource }
}

export default useFramework
