<template>
	<div class="container">
		<article>
			<textarea v-model="text" @click="editing = true"></textarea>
			<div v-if="editing">
				<div class="grid">
				<button @click="handleDelete" class="outline secondary">Delete</button>
				<button @click="editing = false; text = ogtext" class="contrast">Cancel</button>
				<button @click="handleSave(); ogtext = text">Save</button>
				</div>
			</div>
		</article>
	</div>
</template>

<script>
import { ref } from 'vue'

export default {
	name: 'TextBox',
	props: ['tool'],
	setup(props, { emit }) {
		const editing = ref(false)
		const text = ref(props.tool.text)
		const ogtext = ref(props.tool.text)

		const handleSave = () => emit('save', { id: props.tool.id, text: text.value })
		const handleDelete = () => emit('delete',  props.tool.id)

		return { editing, text, ogtext, handleSave, handleDelete }
	}
}
</script>

<style scoped>
/*button {
	display:inline;
	width:50%;
}*/
</style>
