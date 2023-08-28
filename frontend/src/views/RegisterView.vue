<template>
  <div class="container">
    <article>
      <h1>Register</h1>
      <form @submit.prevent="handleSubmit">
        <label>Name:</label>
        <input v-model="name" />
        <label>Email:</label>
        <input type="email" v-model="email" />
        <label>Password:</label>
        <input type="password" v-model="password" />
        <button>Register</button>
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
  name: "Signup",
  setup() {
    const router = useRouter();
    const { user, register, error } = useUser();
    const name = ref("");
    const email = ref("");
    const password = ref("");

    const handleSubmit = () =>
      register(name.value, email.value, password.value);

    watchEffect(() => {
      if (user.value && !error.value) router.push({ name: "dashboard" });
    });

    return { name, email, password, error, handleSubmit };
  },
};
</script>
