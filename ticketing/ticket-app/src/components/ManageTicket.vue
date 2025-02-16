<template>
  <div>
    <p>{{ msg }}</p>
    <div class="details-box">
      <h1 class="list-topic">Request for Assistance</h1>
      <h3>Sort :
        <button class="btn-filter" @click="() => fetchPending('PENDING')">PENDING</button>
        <button class="btn-filter" @click="() => fetchPending('ACCEPTED')">ACCEPTED</button>
        <button class="btn-filter" @click="() => fetchPending('RESOLVED')">RESOLVED</button>
        <button class="btn-filter" @click="() => fetchPending('REJECTED')">REJECTED</button>
        <button class="btn-filter" @click="fetchData">Latest updated</button>
      </h3>
      <div v-if="data.length > 0">
        <div>
          <p>Sort by {{ status }} </p>
        </div>
        <div v-for="ticket in data" :key="ticket.id">
          <div class="detail">
            <p><strong>Title:</strong> {{ ticket.title }}</p>
            <p><strong>Description</strong>: {{ ticket.description }}</p>
            <p><strong>ContactInformation</strong>: {{ ticket.contactInformation }}</p>
            <p><strong>CreatedTimestamp</strong>: {{ ticket.createdTimestamp }}</p>
            <p><strong>UpdatedTimestamp</strong>: {{ ticket.updatedTimestamp }}</p>
            <p><strong>Status</strong>: {{ ticket.status || "No status available" }}</p>
            <div class="btn-manage" v-show="status !== 'RESOLVED' && status !== 'REJECTED'">
              <button v-if="status === 'PENDING'" class="btn-action"
                @click="updateTicketStatus(ticket.id, 'ACCEPT')">ACCEPT</button>
              <button v-if="status === 'PENDING' || status === 'ACCEPTED'" class="btn-action"
                @click="updateTicketStatus(ticket.id, 'REJECT')">REJECT</button>
              <button v-if="status === 'ACCEPTED'" class="btn-action"
                @click="updateTicketStatus(ticket.id, 'RESOLVE')">RESOLVE</button>
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <p>Sort by {{ status }} > ticket not found </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ManageTicket",
  data() {
    return {
      data: [],
      status: "Lastest updated",
    };
  },
  mounted() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      const response = await fetch("http://localhost:8080/api/tickets");
      this.data = await response.json();
    },
    async fetchPending(type) {
      this.status = type;
      const response = await fetch(`http://localhost:8080/api/tickets/status/${this.status}`);
      this.data = await response.json();

    },
    async updateTicketStatus(ticketId, type) {
      try {
        if (type == 'ACCEPT') {
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
          }
        } else if (type == 'RESOLVE') {//resolve
          const response = await fetch(`http://localhost:8080/api/tickets/resolve/${ticketId}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              status: 'RESOLVED',
            }),
          });
          if (response.ok) {
            this.data = this.data.filter(ticket => ticket.id !== ticketId);
          }
        } else if (type == 'REJECT') {//reject
          const response = await fetch(`http://localhost:8080/api/tickets/reject/${ticketId}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              status: 'REJECTED',
            }),
          });
          if (response.ok) {
            this.data = this.data.filter(ticket => ticket.id !== ticketId);
          }
        }

      } catch (error) {
        console.error('Error updating ticket status:', error);
      }
    },

  },
};
</script>


<style>
.details-box {
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

.detail {
  width: auto;
  margin: 0 50px 10px 50px;
  padding: 10px;
  /* background-color: #204abe; */
  border: 5px solid #dcdcdc;
  border-radius: 4px;
  text-align: start;
}

.list-topic {
  margin: 10px 0 10px 0;
  color: #eed202;
}

.btn-filter {
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

.btn-manage {
  text-align: end;
}

.btn-action {
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