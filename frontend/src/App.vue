<template>
  <!-- <div v-if="user">User: {{ user }}</div>
  <div v-else>No user</div> -->
  <Navbar />
  <router-view />
</template>

<script>
import { ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import Navbar from "@/components/Navbar.vue";
import useUser from "@/composables/UseUser.js";
import eventBus from "@/utils/EventBus.js";
import "@/styles/pico.css";

export default {
  name: "App",
  components: { Navbar },
  setup() {
    const { user, logout, reload } = useUser();
    const router = useRouter();

    eventBus.on("token-expired", () => {
      logout();
      router.push({ name: "login" });
    });

    onBeforeMount(reload);

    return { user };
  },
};
</script>

<style>
#app {
  text-align: center;
}
</style>
