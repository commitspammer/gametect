import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useTool = (toolId) => {
	const path = computed(() => '/tools/' + unref(toolId))

	const fields = useResource(path)

	return { ...fields, tool: fields.resource }
}

export default useTool
