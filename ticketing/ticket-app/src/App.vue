<template>
  <img id="ticket-logo" alt="Vue logo" src="./assets/ticket-logo.png" />
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <!-- <GetData msg="Welcome to Vue.js!" /> -->
   <div>
    <nav>
      <button ><router-link class="button-link" to="/">Ticket history</router-link></button>
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
img{
  width: auto;
  height: 200px;
}
#button-link{
  text-decoration: none;
}
</style>
