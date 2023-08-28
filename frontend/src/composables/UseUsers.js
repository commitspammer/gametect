import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useUsers = () => {
	const path = computed(() => '/users')

	const fields = useResource(path)

	return { ...fields, users: fields.resource }
}

export default useUsers
