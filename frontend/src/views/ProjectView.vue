<template>
	<div class="project">
		<h1 v-if="projpending || projerror || !framework">Project</h1>
		<h1 v-else>Project: {{ project.title }}</h1>

		<button v-if="!showModal" @click="showModal = true" :disabled="!subsection">Add new tool</button>
		<button v-if="showModal" @click="showModal = false">Cancel</button>
		<article v-if="showModal">
			<label>Choose a tool:</label>
			<select v-model="type">
				<option value="tb">Text box</option>
				<option value="sb">Selection box</option>
				<option value="i">Image</option>
				<option value="cs">Character sheet</option>
			</select>
			<div v-if="toolerror">{{ toolerror.message }}</div>
			<button @click="createByType[type]">Add</button>
		</article>

		<div v-if="projpending">Loading...</div>
		<div v-else-if="projerror">Couldn't load project...</div>
		<div v-else-if="framepending">Loading...</div>
		<div v-else-if="frameerror">Couldn't load framework...</div>
		<Grid v-else-if="framework">
			<FrameworkTree :framework="framework" class="c-2" @subsectionClicked="s => subsection = s" />
			<div class="c-10">
				<h2 v-if="subsection">{{ subsection.title }}</h2>
				<h2 v-else>Pick a subsection</h2>
				<!-- <div>
					{{ subsection }}
					<hr>
					{{ tools }}
					<hr>
					{{ toolsBySubsection }}
				</div> -->
				<div v-if="subsection" v-for="t in toolsBySubsection[subsection.id]" :key="t.id">
					<TextBox
						v-if="t.type == 'tb'"
						:tool="t"
						@delete="id => removeTB(id)"
						@save="tool => updateTB(tool)"
					/>
					<Image
						v-else-if="t.type == 'i'"
						:tool="t"
						@delete="id => removeI(id)"
						@save="tool => updateI(tool)"
					/>
					<CharacterSheet
						v-else-if="t.type == 'cs'"
						:tool="t"
						@delete="id => removeCS(id)"
						@save="tool => updateCS(tool)"
					/>
					<SelectionBox
						v-else-if="t.type == 'sb'"
						:tool="t"
						@delete="id => removeSB(id)"
						@save="tool => updateSB(tool)"
						@addItem="item => createITEM(item)"
						@deleteItem="id => removeITEM(id)"
					/>
				</div>
			</div>
			<div>
			</div>
		</Grid>
		<div v-else>No framework here.</div>
	</div>
</template>

<script>
import { ref, watchEffect, computed } from 'vue'
import useProject from '@/composables/UseProject.js'
import useFramework from '@/composables/UseFramework.js'
import useTools from '@/composables/UseTools.js'
import useTextBox from '@/composables/UseTextBox.js'
import useImage from '@/composables/UseImage.js'
import useCharacterSheet from '@/composables/UseCharacterSheet.js'
import useSelectionBox from '@/composables/UseSelectionBox.js'
import useItem from '@/composables/UseItem.js'
import FrameworkTree from '@/components/FrameworkTree.vue'
import Grid from '@/components/Grid.vue'
import TextBox from '@/components/TextBox.vue'
import Image from '@/components/Image.vue'
import CharacterSheet from '@/components/CharacterSheet.vue'
import SelectionBox from '@/components/SelectionBox.vue'

export default {
	name: 'Project',
	props: ['projectId'],
	components: { FrameworkTree, Grid, TextBox, Image, CharacterSheet, SelectionBox },
	setup(props) {
		const { project, pending: projpending, error: projerror } = useProject(props.projectId)
		const frameworkId = computed(() => project.value?.frameworkId || null)
		const { framework, pending: framepending, error: frameerror } = useFramework(frameworkId)
		const subsection = ref(null)

		const showModal = ref(false)
		const { tools, pending: toolspedings, error: toolserror, load } = useTools(props.projectId)
		const group = (xs, key) => {
			return xs.reduce(function(rv, x) {
				(rv[x[key]] = rv[x[key]] || []).push(x)
				return rv
			}, {});
		}
		const toolsBySubsection = computed(() =>
			tools.value ?
			group([
				...tools.value.characterSheets.map(cs => {return{...cs, type:'cs'}}),
				...tools.value.selectionBoxes.map(sb => {return{...sb, type:'sb'}}),
				...tools.value.textBoxes.map(tb => {return{...tb, type:'tb'}}),
				...tools.value.images.map(i => {return{...i, type:'i'}}),
				],
				'subsectionId'
			) : null
		)
		const toolerror = ref(null)
		const createTool = (use, justtool) => {
			const { create: creator, error: e } = use()
			creator({ ...justtool, projectId: props.projectId, subsectionId: subsection.value.id })
			.then(load)
			.then(toolerror.value = e.value)
		}
		const createTB = () => createTool(useTextBox, { text:'' })
		const createI = () => createTool(useImage, { width:300, height:300, url:'' })
		const createCS = () => createTool(useCharacterSheet, { name:'', referenceSheet:'', height:0, weight:0, history:'', motivation:'' })
		const createSB = () => createTool(useSelectionBox, { question:'', items:[] })
		const type = ref(null)
		const createByType = {
			tb: createTB,
			i: createI,
			cs: createCS,
			sb: createSB,
		}
		const createITEM = (item) => {
			const { create: creator, error: e } = useItem()
			creator(item)
			.then(load)
			.then(toolerror.value = e.value)
		}

		const removeTool = (use, justid) => {
			const { remove: remover, error: e } = use(justid)
			remover().then(load).then(toolerror.value = e.value)
		}
		const removeTB = (id) => removeTool(useTextBox, id)
		const removeI = (id) => removeTool(useImage, id)
		const removeCS = (id) => removeTool(useCharacterSheet, id)
		const removeSB = (id) => removeTool(useSelectionBox, id)
		const removeITEM = (id) => {
			console.log(id)
			const { remove: remover, error: e } = useItem(id)
			remover().then(load).then(toolerror.value = e.value)
		}

		const updateTool = (use, justtool) => {
			const { update: updator, error: e } = use(justtool.id)
			updator({ ...justtool, projectId: props.projectId, subsectionId: subsection.value.id })
			.then(load)
			.then(toolerror.value = e.value)
		}
		const updateTB = (tool) => updateTool(useTextBox, tool)
		const updateI = (tool) => updateTool(useImage, tool)
		const updateCS = (tool) => updateTool(useCharacterSheet, tool)
		const updateSB = (tool) => updateTool(useSelectionBox, tool)

		return {
			framework, projpending, projerror, framepending, frameerror, project, subsection,
			toolsBySubsection, tools, showModal, type, createByType, toolerror,
			createTB, removeTB, updateTB,
			createI, removeI, updateI,
			createCS, removeCS, updateCS,
			createSB, removeSB, updateSB,
			createITEM, removeITEM,
		}
	}
}
</script>
