<template>
  <nav style="color: white">
    <ul style="margin-left: 20px">
      <li>
        <router-link :to="{ name: 'home' }" style="color: inherit"
          ><strong>GameTect</strong></router-link
        >
      </li>
    </ul>
    <ul style="margin-right: 20px">
      <li>
        <router-link :to="{ name: 'about' }" style="color: inherit"
          >About</router-link
        >
      </li>
      <li v-if="!loggedIn">
        <router-link :to="{ name: 'login' }" style="color: inherit"
          >Login</router-link
        >
      </li>
      <li v-if="!loggedIn">
        <router-link :to="{ name: 'register' }" style="color: inherit"
          >Register</router-link
        >
      </li>
      <li v-if="loggedIn">
        <router-link
          :to="{ name: 'home' }"
          @click="handleLogout"
          style="color: inherit"
          >Logout</router-link
        >
      </li>
      <li v-if="loggedIn">
        <router-link :to="{ name: 'dashboard' }" style="color: inherit"
          >Dashboard</router-link
        >
      </li>
      <li>
        <a href="#" role="button" @click="toggle">🌗</a>
      </li>
    </ul>
  </nav>
</template>

<script>
import { onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import useUser from "@/composables/UseUser.js";
import useDarkmode from "@/composables/UseDarkmode.js";

export default {
  setup() {
    const router = useRouter();
    const { loggedIn, logout, reload } = useUser();
    const { toggle } = useDarkmode();

    const handleLogout = () => {
      logout();
      router.push({ name: "home" });
    };

    onBeforeMount(reload);

    return { loggedIn, handleLogout, toggle };
  },
};
</script>
