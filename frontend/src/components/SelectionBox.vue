<template>
	<div class="container">
		<article>
			<input v-model="question" @click="editing = true" />
			<fieldset>
				<label v-for="i in tool.items">
					<a v-if="editing" href="#" role="button" @click="handleDeleteItem(i.id)">Delete item</a>
					<span v-if="editing"> | </span>
					<input type="checkbox" :selected="i.selected"/>
					{{ i.description }} <!-- <span v-if="editing">DELETE ITEM</span> -->
				</label>
			</fieldset>
			<div v-if="editing">
				<div class="grid">
					<!-- <input type="checkbox" disabled/> -->
					<input type="text" v-model="newItem"/>
					<button @click="handleAddItem(newItem)">Add new item</button>
				</div>
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
	name: 'SelectionBox',
	props: ['tool'],
	setup(props, { emit }) {
		const editing = ref(false)
		const question = ref(props.tool.question)
		const items = ref(props.tool.items)
		const newItem = ref('')

		const handleSave = () => emit('save', { id: props.tool.id, question: question.value })
		const handleDelete = () => emit('delete',  props.tool.id)
		const handleAddItem = (description) => emit('addItem', {
			selectionBoxId: props.tool.id,
			description,
			selected: false,
		})
		const handleDeleteItem = (id) => emit('deleteItem', id)

		return { editing, question, items, newItem, handleSave, handleDelete, handleAddItem, handleDeleteItem }
	}
}
</script>

<style scoped>
/*button {
	display:inline;
	width:50%;
}*/
fieldset {
	text-align:left;
}
</style>
