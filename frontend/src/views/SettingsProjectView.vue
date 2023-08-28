<template>
  <div class="projectSettings">
    <article class="container">
      <h1>Project Settings</h1>
      <div class="grid" style="margin-left: 20px; margin-right: 20px">
        <div v-if="!editingProject">
          <img :src="banner" alt="Banner not found" />
        </div>
        <input v-model="title" @click="editingProject = true" />
        <div v-if="editingProject">
          <input v-model="banner" />
        </div>
      </div>
      <div v-if="editingProject" style="margin-left: 20px; margin-right: 20px">
        <textarea v-model="description"></textarea>
        <div class="grid">
          <button
            @click="
              editingProject = false;
              text = ogtext;
            "
            class="contrast"
          >
            Cancel
          </button>
          <button @click="handleUpdate">Save</button>
        </div>
      </div>
      <div style="margin-bottom: 10rem"></div>
      <div
        style="
          display: flex;
          margin-left: 20px;
          margin-right: 20px;
          margin-bottom: 0.5rem;
        "
      >
        <h6>Members</h6>
        <a
          href="#"
          role="button"
          style="
            margin: auto;
            margin-top: 0;
            margin-right: 0;
            justify-content: space-between;
          "
          @click="editingUsers = true"
          >➕</a
        >
      </div>
      <div v-if="editingUsers" style="margin-left: 20px; margin-right: 20px">
        <div class="grid">
          <input v-model="pId" disabled />
          <input v-model="userId" placeholder="User Id" />
        </div>
        <input v-model="userProjectRoleEnum" placeholder="Role Description" />
        <div class="grid">
          <button
            @click="
              editingUsers = false;
              text = ogtext;
            "
            class="contrast"
          >
            Cancel
          </button>
          <button>Save</button>
        </div>
      </div>
      <div v-for="user in users" v-if="!editingUsers">
        <details
          style="text-align: left; margin-left: 20px; margin-right: 20px"
        >
          <summary role="button" class="secondary outline">
            {{ user.name }}
          </summary>
          <div>
            <div style="display: flex">
              <ul>
                <li>
                  {{ user.email }}
                </li>
              </ul>
              <ul style="margin-left: auto; margin-right: auto">
                <div v-for="role in user.roles">
                  <li>{{ role }}</li>
                </div>
              </ul>
              <a
                href="#"
                role="button"
                style="
                  margin: auto;
                  margin-top: 0;
                  margin-right: 0;
                  justify-content: space-between;
                "
                >⚙️</a
              >
            </div>
          </div>
        </details>
      </div>
    </article>
  </div>
</template>

<script>
import { ref, watchEffect, computed } from "vue";
import useProject from "@/composables/UseProject.js";
import useFramework from "@/composables/UseFramework.js";
import useTools from "@/composables/UseTools.js";
import api from "@/utils/ApiClient";

export default {
  name: "SettingsProject",
  props: ["projectId"],
  setup(props) {
    const editingProject = ref(false);
    const editingUsers = ref(false);
    const userId = ref("");
    const userProjectRoleEnum = ref("");
    const users = ref([]);

    const { project, update } = useProject(props.projectId);

    const title = ref("");
    const banner = ref("");
    const description = ref("");
    const frameworkId = ref("");

    const handleUpdate = () =>
      update({
        title: title.value,
        banner: banner.value,
        description: description.value,
        frameworkId: frameworkId.value,
      });

    const getUsers = async () => {
      const error = ref(null);
      try {
        const res = await api.get("/projects/" + props.projectId + "/users");
        users.value = res.data;
      } catch {
        error.value = new Error("Oops! There was an error response");
      }
    };

    getUsers();

    watchEffect(() => {
      project.value ? (title.value = project.value.title) : null;
      project.value ? (banner.value = project.value.banner) : null;
      project.value ? (description.value = project.value.description) : null;
      project.value ? (frameworkId.value = project.value.frameworkId) : null;
    });
    const name = computed(() => users.value?.name || null);
    const email = computed(() => users.value?.email || null);

    const pId = computed(() => props.projectId || null);

    return {
      project,
      users,
      editingProject,
      editingUsers,
      title,
      banner,
      description,
      name,
      email,
      pId,
      userId,
      userProjectRoleEnum,
      handleUpdate,
    };
  },
};
</script>
