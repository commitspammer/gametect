import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useImage = (imageId) => {
	const path = computed(() => imageId ? '/images/' + unref(imageId) : '/images')

	const fields = useResource(path)

	return { ...fields, image: fields.resource }
}

export default useImage
