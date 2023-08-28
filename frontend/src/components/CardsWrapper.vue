<template>
	<div class="cards-wrapper">
		<slot></slot>
	</div>
</template>

<script>
import { ref } from 'vue'

export default {
	props: ['cards'],
	setup(props, { slots }) {
		//const cards = ref(slots.default().map(cards => cards.props.id))
	}
}
</script>

<style>
.cards-wrapper {
	display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-gap: 20px;
}
</style>
