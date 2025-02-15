<template>
  <div>
    <p>{{ msg }}</p>
    <button class="btn-load" @click="() => fetchPending('PENDING')">
      Manage Ticket
    </button>

    <div class="details-box">
      <div v-if="data.length > 0">
        <h1 class="list-topic">Ticket List</h1>
        <div>
          <h3>Sort :
            <button class="btn-filter" @click="() => fetchPending('PENDING')">PENDING</button>
            <button class="btn-filter" @click="() => fetchPending('ACCEPTED')">ACCEPTED</button>
            <button class="btn-filter" @click="() => fetchPending('RESOLVED')">RESOLVED</button>
            <button class="btn-filter" @click="() => fetchPending('REJECTED')">REJECTED</button>
            <button class="btn-filter" @click="fetchData">Latest updated</button>
          </h3>
          <p>Sort By {{ status }} </p>
        </div>
        <div v-for="ticket in data" :key="ticket.id">
          <div class="detail">
            <p><strong>Title:</strong> {{ ticket.title }}</p>
            <p><strong>Description</strong>: {{ ticket.description }}</p>
            <p><strong>ContactInformation</strong>: {{ ticket.contactInformation }}</p>
            <p><strong>CreatedTimestamp</strong>: {{ ticket.createdTimestamp }}</p>
            <p><strong>UpdatedTimestamp</strong>: {{ ticket.updatedTimestamp }}</p>
            <p><strong>Status</strong>: {{ ticket.status || "No status available" }}</p>
            <div class="btn-manage">
              <button v-if="status === 'PENDING'" class="btn-action" @click="updateTicketStatus(ticket.id)">ACCEPT</button>
              <button v-if="status === 'ACCEPTED'" class="btn-action" @click="updateTicketStatus(ticket.id)">REJECT</button>
              <button v-if="status === 'ACCEPTED'" class="btn-action" @click="updateTicketStatus(ticket.id)">RESOLVE</button>
            </div>
          </div>
          
        </div>
      </div>
      <div v-else-if="notFound">

        <h1 class="list-topic">Ticket List</h1>
        <div>
          <h3>Sort :
            <button class="btn-filter" @click="() => fetchPending('PENDING')">PENDING</button>
            <button class="btn-filter" @click="() => fetchPending('ACCEPTED')">ACCEPTED</button>
            <button class="btn-filter" @click="() => fetchPending('RESOLVED')">RESOLVED</button>
            <button class="btn-filter" @click="() => fetchPending('REJECTED')">REJECTED</button>
            <button class="btn-filter" @click="fetchData">Latest updated</button>
          </h3>
          <p>Sort By {{status}} Ticket not found </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GetData",
  data() {
    return {
      data: [],  
      status: "Lastest updated",
      notFound: false, 
    };
  },
  methods: {
    async fetchData() {
      const response = await fetch("http://localhost:8080/api/tickets");
      this.status = "Latest update";
      this.data = await response.json();

      this.notFound = this.data.length === 0;
    },
    async fetchPending(type) {
      this.status = type;
      const response = await fetch(`http://localhost:8080/api/tickets/status/${this.status}`);
      this.data = await response.json();

      this.notFound = this.data.length === 0;
    },
    async updateTicketStatus(ticketId) {
      try {
        const response = await fetch(`http://localhost:8080/api/tickets/accept/${ticketId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            status: 'ACCEPTED',
          }),
        });

        if (response.ok) {
          this.data = this.data.filter(ticket => ticket.id !== ticketId);
          console.log('Ticket status updated to ACCEPTED and removed from the list');
        }
      } catch (error) {
        console.error('Error updating ticket status:', error);
      }
    },

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
  .btn-manage{
    text-align: end;
  }
  .btn-action{
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
  .btn-action:hover {
      background-color: #c49c23; 
  }
  </style>
    