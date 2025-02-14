<template>
  <img alt="Vue logo" src="./assets/logo.png" />
  <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  <GetData msg="Welcome to Vue.js!" />
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import GetData from "./components/GetData.vue";
export default {
  name: "App",
  components: {
    GetData,
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
