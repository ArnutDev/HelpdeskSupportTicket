<template>
  <div class="ticket-logo">
    <img alt="Vue logo" src="./assets/ticket-logo.png" />
    <h1>Helpdesk Support Ticket</h1>
  </div>
  <div>
    <!-- <router-link to="/ticket"><button class="btn-history">Ticket history</button></router-link> -->
    <!-- <router-view /> -->
    <CreateTicket />
    <GetTicketDetails />
    <ManageTicket />
  </div>
</template>

<script>
import ManageTicket from './components/ManageTicket.vue';
// import CreateTicket from "./components/CreateTicket.vue"
export default {
  name: "App",
  components: {
    // CreateTicket,
    ManageTicket
  },
};
</script>

<style>
#app,
body {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #dcdcdc;
  background-color: #212529;
}

.ticket-logo img {
  width: auto;
  height: 100px;
}

.btn-history {
  background-color: #0D6EFD;
  color: #ffffff;
  border: none;
  margin: 15px 0 10px 0;
  padding: 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
}

.btn-history:hover {
  background-color: #0056b3;
}

.button-link {
  text-decoration: none;
}
</style>
