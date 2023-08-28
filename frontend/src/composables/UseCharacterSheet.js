import { ref, unref, computed } from 'vue'
import useResource from '@/composables/UseResource.js'

const useCharacterSheet = (characterSheetId) => {
	const path = computed(() => characterSheetId ? '/characterSheets/' + unref(characterSheetId) : '/characterSheets')

	const fields = useResource(path)

	return { ...fields, characterSheet: fields.resource }
}

export default useCharacterSheet
