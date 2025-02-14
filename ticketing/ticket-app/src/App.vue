<template>
  <img alt="Vue logo" src="./assets/logo.png" />
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <!-- <GetData msg="Welcome to Vue.js!" /> -->
   <div>
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/create">Create Ticket</router-link>
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
</style>
