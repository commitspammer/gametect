import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useProject = (projectId) => {
	const path = computed(() => projectId ? '/projects/' + unref(projectId) : '/projects')

	const fields = useResource(path)

	return { ...fields, project: fields.resource }
}

export default useProject
