<template>
  <div>
    <h1>hello yahoooooooo</h1>
    <p>{{ msg }}</p>
    <button @click="fetchData">โหลดข้อมูล</button>

    <!-- ตรวจสอบว่า data มีค่าก่อนแสดงผล -->
    <div v-if="data && data.length > 0">
      <div v-for="ticket in data" :key="ticket.id">
        <h2>{{ ticket.title }}</h2>
        <p>{{ ticket.description }}</p>
        <p>{{ ticket.contactInformation }}</p>
        <p>{{ ticket.createdTimestamp }}</p>
        <p>{{ ticket.updatedTimestamp }}</p>
        <p>{{ ticket.status || "No status available" }}</p>
        <!-- ถ้าไม่มี status จะแสดงข้อความนี้ -->
      </div>
    </div>
    <div v-else>
      <p>ยังไม่มีข้อมูล</p>
    </div>
  </div>
</template>
  
  <script>
export default {
  name: "GetData",
  data() {
    return {
      data: null,
    };
  },
  methods: {
    async fetchData() {
      const response = await fetch("http://localhost:8080/api/tickets");
      this.data = await response.json();
    },
  },
};
</script>
  