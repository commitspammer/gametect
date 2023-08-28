<template>
  <div class="container">
    <article>
      <h1>New Project</h1>
      <form @submit.prevent="handleSubmit">
        <label>Title:</label>
        <input type="text" v-model="title" />
        <label>Description:</label>
        <textarea placeholder="" v-model="description"></textarea>
        <label>Banner URL:</label>
        <input type="text" v-model="banner" />
        <label>Framework:</label>
        <select v-model="frameworkId">
          <option value="">Select a framework</option>
          <option v-for="f in frameworks" :value="f.id">{{ f.name }}</option>
        </select>

        <button>Create</button>
      </form>
      <div v-if="error">{{ error.message }}</div>
    </article>
  </div>
</template>

<script>
import { ref, watchEffect, computed } from "vue";
import { useRouter } from "vue-router";
import useUser from "@/composables/UseUser.js";
import useProject from "@/composables/UseProject.js";
import useUsersProjects from "@/composables/UseUsersProjects.js";
import useFrameworks from "@/composables/UseFrameworks.js";

export default {
  name: "NewProject",
  setup() {
    const router = useRouter();
    const { userId } = useUser();

    const { project, create, error } = useProject();
    const { frameworks } = useFrameworks();

    const title = ref("");
    const description = ref("");
    const banner = ref("");
    const frameworkId = ref(null);

    const handleSubmit = () =>
      create({
        title: title.value,
        description: description.value,
        banner: banner.value,
        frameworkId: frameworkId.value,
      })
        .then(() => {
          const { create: c } = useUsersProjects();
          console.log(userId.value);
          console.log(project.value.id);
          c({
            userId: userId.value,
            projetoId: project.value.id, //why the fuck?
            description: "ADMIN",
          });
        })
        .then(() => router.push({ name: "dashboard" }))
        .catch((e) => (error.value = e));

    return {
      frameworks,
      error,
      handleSubmit,
      title,
      description,
      banner,
      frameworkId,
    };
  },
};
</script>
