<template>
  <div>
    <p>{{ msg }}</p>
    <button @click="fetchData">โหลดข้อมูล</button>

    <!-- ตรวจสอบว่า data มีค่าก่อนแสดงผล -->
    <div v-if="data && data.length > 0">
      <div v-for="ticket in data" :key="ticket.id">
        <p><strong>Title:</strong> {{ ticket.title }}</p>
        <p><strong>Description</strong>: {{ ticket.description }}</p>
        <p>
          <strong>ContactInformation</strong>: {{ ticket.contactInformation }}
        </p>
        <p><strong>CreatedTimestamp</strong>: {{ ticket.createdTimestamp }}</p>
        <p><strong>UpdatedTimestamp</strong>: {{ ticket.updatedTimestamp }}</p>
        <p>
          <strong>Status</strong>: {{ ticket.status || "No status available" }}
        </p>
        <button @click="$router.push('/update')">Edit Ticket</button>
        <h1>
          -----------------------------------------------------------------
        </h1>
      </div>
    </div>
    <div v-else>
      <p>ยังไม่มีข้อมูล</p>
    </div>
  </div>
</template>
  
  <script >
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


  