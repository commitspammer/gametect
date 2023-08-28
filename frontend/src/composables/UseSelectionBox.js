import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useSelectionBox = (selectionBoxId) => {
	const path = computed(() => selectionBoxId ? '/selection-box/' + unref(selectionBoxId) : '/selection-box')

	const fields = useResource(path)

	return { ...fields, selectionBox: fields.resource }
}

export default useSelectionBox
