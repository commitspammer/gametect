<template>
  <div class="dashboard">
    <h1>Dashboard</h1>
    <p v-if="user">Welcome back, {{ user.name }}!</p>
    <h2>All Projects</h2>
    <router-link :to="{ name: 'newproject' }">
      <div><button>Create new project</button></div>
    </router-link>
    <!-- CONTRACT: either it's pending and BOTH error and projects are null -->
    <!-- or it's NOT pending and EITHER error or projects is null, and the other non-null -->
    <div v-if="pending">Loading...</div>
    <div v-else-if="error">Couldn't load projects...</div>
    <CardsWrapper v-else-if="projects.length">
      <router-link
        v-for="project in projects"
        :to="{ name: 'project', params: { projectId: project.id } }"
        style="text-decoration: none; color: inherit"
      >
        <Card
          :title="project.title"
          :subtitle="
            project.collaborators?.map((c) => c.name).join(' - ') || null
          "
          :image="project.banner"
          :content="project.description"
          :id="project.id"
        />
      </router-link>
    </CardsWrapper>
    <div v-else>You have no projects.</div>
  </div>
</template>

<script>
import { ref, watchEffect, computed } from "vue";
import useUser from "@/composables/UseUser.js";
import useProjects from "@/composables/UseProjects.js";
import Card from "@/components/Card.vue";
import CardsWrapper from "@/components/CardsWrapper.vue";

export default {
  name: "Dashboard",
  components: { Card, CardsWrapper },
  setup() {
    const { user } = useUser();

    const userId = computed(() => (user.value ? user.value.id : null));

    const { projects, pending, error } = useProjects(userId);

    return { user, projects, pending, error };
  },
};
</script>
