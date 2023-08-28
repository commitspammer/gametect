import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useUsersProjects = (projectId,userId) => {
	const path = computed(() => projectId && userId ?
		'/users-projects/' + unref(projectId) + "/" + unref(userId) : '/users-projects')

	const fields = useResource(path)

	return { ...fields, usersProjects: fields.resource }
}

export default useUsersProjects
