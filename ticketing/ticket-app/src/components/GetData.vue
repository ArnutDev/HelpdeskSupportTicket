<template>
  <div>
    <h1>hello yahoooooooo</h1>
    <p>{{ msg }}</p>
    <button @click="fetchData">โหลดข้อมูล</button>
    <h1>{{ data.title }}</h1>
    <p>{{ data.description }}</p>
    <p>{{ data.contactInformation }}</p>
    <p>{{ data.createdTimestamp }}</p>
    <p>{{ data.updatedTimestamp }}</p>
    <p>{{ data.status }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "GetData",
  props: {
    msg: String,
  },
  data() {
    return {
      data: null,
    };
  },
  methods: {
    async fetchData() {
      try {
        const response = await axios.get("http://localhost:8080/api/tickets");
        this.data = response.data;
      } catch (error) {
        console.error("Error something wrong:", error);
      }
    },
  },
};
</script>
<style>
</style>