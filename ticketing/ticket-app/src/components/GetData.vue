<template>
  <div>
    <h1>hello yahoooooooo</h1>
    <p>{{ msg }}</p>
    <button @click="fetchData">โหลดข้อมูล</button>

    <!-- แสดงสถานะกำลังโหลด -->
    <div v-if="isLoading">กำลังโหลดข้อมูล...</div>

    <!-- ตรวจสอบว่า data มีค่าก่อนแสดงผล -->
    <div v-if="data">
      <h1>{{ data.title }}</h1>
      <p>{{ data.description }}</p>
      <p>{{ data.contactInformation }}</p>
      <p>{{ data.createdTimestamp }}</p>
      <p>{{ data.updatedTimestamp }}</p>
      <p>status: {{ data.status }}</p>
    </div>
    <div v-else>
      <p>ยังไม่มีข้อมูล</p>
    </div>
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
      isLoading: false, // สถานะการโหลดข้อมูล
    };
  },
  methods: {
    async fetchData() {
      this.isLoading = true; // ตั้งค่าเป็นกำลังโหลด
      try {
        this.data = await axios.get("http://localhost:8080/api/tickets");
      } catch (error) {
        console.error("Error something wrong:", error);
      } finally {
        this.isLoading = false; // หยุดสถานะการโหลดเมื่อเสร็จ
      }
    },
  },
};
</script>
  