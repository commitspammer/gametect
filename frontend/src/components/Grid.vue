<template>
	<div class="gridd">
		<slot></slot>
	</div>
</template>

<script>
import { ref } from 'vue'

export default {
	props: [],
	setup(props) {
	}
}
</script>

<style>
.gridd {
	display: flex;
	flex-wrap: wrap;
	width: 100%;
}

.gridd::after {
	content: "";
	clear: both;
	display: table;
}

[class*="c-"] {
	/*float: left;*/
	box-sizing: border-box;
	padding: 15px;
}

.c-1  {width: 8.33%; }
.c-2  {width: 16.66%;}
.c-3  {width: 25%;   }
.c-4  {width: 33.33%;}
.c-5  {width: 41.66%;}
.c-6  {width: 50%;   }
.c-7  {width: 58.33%;}
.c-8  {width: 66.66%;}
.c-9  {width: 75%;   }
.c-10 {width: 83.33%;}
.c-11 {width: 91.66%;}
.c-12 {width: 100%;  }
</style>
