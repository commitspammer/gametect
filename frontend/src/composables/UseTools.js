import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useTools = (projectId) => {
	const path = computed(() => '/projects/' + unref(projectId) + '/tools')

	const fields = useResource(path)

	return { ...fields, tools: fields.resource }
}

export default useTools
