<template>
  <div class="ticket-logo">
  <img alt="Vue logo" src="./assets/ticket-logo.png" />
  </div>
   <div>
    <nav>
      <button><router-link class="button-link" to="/">Ticket history</router-link></button>
      |
      <button><router-link to="/create">Create ticket</router-link></button>
    </nav>
    <router-view />
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import GetData from "./components/GetData.vue";
// import CreateTicket from "./components/CreateTicket.vue"
export default {
  name: "App",
  components: {
    // GetData,
    // CreateTicket,
  },
};
</script>

<style>
#app ,body{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
  background-color: #212529;
  height: 100vh;
  margin: 0 auto;
  padding: 0;
}
.ticket-logo img {
  width: auto; 
  height: 200px; 
}

</style>
