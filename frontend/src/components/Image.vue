<template>
	<div class="container">
		<article>
			<img @click="editing = true" :src="url" :width="width" alt="Image not found">
			<div v-if="editing">
				<div class="grid">
					<div>
						<label>Height:</label>
						<input v-model="height" />
					</div>
					<div>
						<label>Width:</label>
						<input v-model="width" />
					</div>
				</div>
				<label>URL:</label>
				<input v-model="url" />
				<div class="grid">
					<button @click="handleDelete" class="outline secondary">Delete</button>
					<button @click="editing = false" class="contrast">Cancel</button>
					<button @click="handleSave">Save</button>
				</div>
			</div>
		</article>
	</div>
</template>

<script>
import { ref } from 'vue'

export default {
	name: 'Image',
	props: ['tool'],
	setup(props, { emit }) {
		const editing = ref(false)
		const url = ref(props.tool.url)
		const height = ref(props.tool.height)
		const width = ref(props.tool.width)

		const handleSave = () => emit('save', {
			id: props.tool.id,
			url: url.value,
			height: height.value,
			width: width.value,
		})
		const handleDelete = () => emit('delete',  props.tool.id)

		return { editing, url, height, width, handleSave, handleDelete }
	}
}
</script>

<style scoped>
/* button {
	display:inline;
	width:50%;
} */
</style>
