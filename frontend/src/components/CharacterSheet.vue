<template>
	<div class="container">
		<article>
			<div class="grid">
				<div>
					<img @click="editing = true" :src="referenceSheet" alt="Ref sheet not found">
					<div v-if="editing">
						<label>Reference sheet URL:</label>
						<input v-model="referenceSheet" />
					</div>
				</div>
				<div>
					<label>Name:</label>
					<input @click="editing = true" v-model="name" />
					<label>Height:</label>
					<input @click="editing = true" v-model="height" />
					<label>Weight:</label>
					<input @click="editing = true" v-model="weight" />
				</div>
			</div>
			<label>History:</label>
			<textarea @click="editing = true" v-model="history"></textarea>
			<label>Motivation:</label>
			<textarea @click="editing = true" v-model="motivation"></textarea>
			<div v-if="editing">
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
	name: 'CharacterSheet',
	props: ['tool'],
	setup(props, { emit }) {
		const editing = ref(false)
		const name = ref(props.tool.name)
		const referenceSheet = ref(props.tool.referenceSheet)
		const height = ref(props.tool.height)
		const weight = ref(props.tool.weight)
		const history = ref(props.tool.history)
		const motivation = ref(props.tool.motivation)

		const handleSave = () => emit('save', {
			id: props.tool.id,
			name: name.value,
			referenceSheet: referenceSheet.value,
			height: height.value,
			weight: weight.value,
			history: history.value,
			motivation: motivation.value,
		})
		const handleDelete = () => emit('delete',  props.tool.id)

		return { editing, name, referenceSheet, height, weight, history, motivation, handleSave, handleDelete }
	}
}
</script>

<style scoped>
/*button {
	display:inline;
	width:50%;
}*/
img {
	/* max-width:340px; */
	max-height:340px;
}
</style>
