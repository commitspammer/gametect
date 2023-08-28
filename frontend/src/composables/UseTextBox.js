import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useTextBox = (textBoxId) => {
	const path = computed(() => textBoxId ? '/textBox/' + unref(textBoxId) : '/textBox')

	const fields = useResource(path)

	return { ...fields, textBox: fields.resource }
}

export default useTextBox
