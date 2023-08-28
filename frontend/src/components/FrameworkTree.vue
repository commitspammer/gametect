<template>
	<article class="framework-tree">
		<div>{{ framework.name }}</div>
		<hr>
		<div v-for="(section,i) in framework.sections" @click="sectionClicked(section)">
			{{ i+1 }} {{ section.title }}
			<div v-for="(subsection,j) in section.subsections" @click="subsectionClicked(subsection)">
				<mark v-if="subsection == selected">{{ (i+1) + "." + (j+1) }} {{ subsection.title }}</mark>
				<div v-else>{{ (i+1) + "." + (j+1) }} {{ subsection.title }}</div>
			</div>
			<hr>
		</div>
	</article>
</template>

<script>
import { ref } from 'vue'

export default {
	props: ['framework'],
	setup(props, { emit }) {
		const selected = ref(null)

		const sectionClicked = (s) => {
			emit('sectionClicked', s)
		}

		const subsectionClicked = (ss) => {
			emit('subsectionClicked', ss)
			selected.value = ss
		}

		return { sectionClicked, subsectionClicked, selected }
	}
}
</script>

<style>
.framework-tree {
	border: solid;
}
</style>
