import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useItem = (itemId) => {
	const path = computed(() => itemId ? '/items/' + unref(itemId) : '/items')

	const fields = useResource(path)

	return { ...fields, item: fields.resource }
}

export default useItem
