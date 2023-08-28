import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useProjects = (userId) => {
	const path = computed(() => '/users/' + unref(userId) + '/projects')

	const fields = useResource(path)

	return { ...fields, projects: fields.resource }
}

export default useProjects
