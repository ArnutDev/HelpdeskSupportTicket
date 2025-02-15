<template>
  <div>
    <p>{{ msg }}</p>
    <button class="btn-load" @click="fetchData">Ticket history</button>

    <!-- ตรวจสอบว่า data มีค่าก่อนแสดงผล -->
    <div class="details-box">
      <div v-if="data && data.length > 0">
        <h1 class="list-topic">Ticket History</h1>
        <div>
          <h3>Sort : 
          <button class="btn-filter" @click="() => fetchPending('PENDING')">PENDING</button>
          <button class="btn-filter" @click="() => fetchPending('ACCEPTED')">ACCEPTED</button>
          <button class="btn-filter" @click="() => fetchPending('RESOLVED')">RESOLVED</button>
          <button class="btn-filter" @click="() => fetchPending('REJECTED')">REJECTED</button>
          <button class="btn-filter" @click="fetchData">Latest updated</button></h3>
          <p>Sort By {{ status }} </p>
          <div v-if="notFound">ไม่พบข้อมูล</div>
        </div>
        <div v-for="ticket in data" :key="ticket.id">
          <div class="detail">
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
            </div>
        </div>
      </div>
      
  </div>
  </div>
</template>
  
  <script >
export default {
  name: "GetData",
  data() {
    return {
      data: "",
      status: "Lastest updated",
      notFound: "",
    };
  },
  methods: {
    async fetchData() {
      const response = await fetch("http://localhost:8080/api/tickets");
      this.status = "Latest update"
      if(!response.ok){
        this.status = "Not found"
      }
      this.data = await response.json();
    },
    async fetchPending(type) {
      this.status = type;
      const response = await fetch(`http://localhost:8080/api/tickets/status/${this.status}`);
      if(!response.ok){
        this.status = "Not found"
      }
      this.data = await response.json();
    }
  },
};
</script>

<style>
.details-box{
  /* background-color: orange; */
}
.btn-load {
    background-color: #0275d8; 
    color: #dcdcdc; 
    border: none; 
    margin: 10px 0 10px 0;
    padding: 15px;
    border-radius: 5px; 
    cursor: pointer; 
    font-size: 16px;
    font-weight: bold;
}

.btn-load:hover {
    background-color: #204abe; 
}
.detail{
  width: auto;
  margin: 0 50px 10px 50px;
  padding: 10px;
  /* background-color: #204abe; */
  border: 5px solid #dcdcdc;
  border-radius: 4px;
  text-align: start;
}
.list-topic{
  margin: 10px 0 10px 0;
  color:#eed202;
}
.btn-filter{
    background-color: #212529;
    color: #dcdcdc;
    border: 2px solid #eed202; 
    margin: 10px 5px 10px 5px;
    padding: 15px;
    border-radius: 5px; 
    cursor: pointer; 
    font-size: 16px;
    font-weight: bold;
}
.btn-filter:hover {
    background-color: #c49c23; 
}
</style>
  