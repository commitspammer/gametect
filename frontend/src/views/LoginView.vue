<template>
  <div class="container">
    <article>
      <h1>Login</h1>
      <form @submit.prevent="handleSubmit">
        <label>Email:</label>
        <input type="email" v-model="email" />
        <label>Password:</label>
        <input type="password" v-model="password" />
        <button>Log in</button>
        <div v-if="error">{{ error.message }}</div>
      </form>
    </article>
  </div>
</template>

<script>
import { ref, watchEffect } from "vue";
import { useRouter } from "vue-router";
import useUser from "@/composables/UseUser.js";

export default {
  name: "Login",
  setup() {
    const router = useRouter();
    const { user, login, error } = useUser();
    const email = ref("");
    const password = ref("");

    const handleSubmit = () => login(email.value, password.value);

    watchEffect(() => {
      if (user.value && !error.value) router.push({ name: "dashboard" });
    });

    return { email, password, error, handleSubmit };
  },
};
</script>
