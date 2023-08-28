<template>
  <div class="card">
    <article>
      <!-- <slot #header></slot> -->
      <div class="flex">
        <img :src="image" alt="Img" />
        <h1 style="margin: auto">{{ title }}</h1>
        <router-link
          :to="{ name: 'settingsproject', params: { projectId: id } }"
          style="text-decoration: none; color: inherit"
          role="button"
          >⚙️</router-link
        >
      </div>
      <h3>{{ subtitle }}</h3>
      <p>{{ content }}</p>
    </article>
  </div>
</template>

<script>
export default {
  props: ["title", "subtitle", "image", "content", "id"],
  setup(props) {},
};
</script>

<style>
.card img {
  float: left;
  width: 20%;
  height: auto;
  max-height: 100px;
  object-fit: cover;
}

.card {
  margin: 25px;
  padding: 15px;
  /* border: solid; */
}

.flex {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
</style>
