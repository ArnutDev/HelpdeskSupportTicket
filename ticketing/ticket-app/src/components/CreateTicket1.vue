<template>
  <div>
      <h1>Create New Ticket</h1>
      <form @submit.prevent="submitTicket">
          <label>Title: </label>
          <input v-model="ticket.title" required/>
          <label>Description: </label>
          <input v-model="ticket.description" required/>
          <label>Contact Information: </label>
          <input v-model="ticket.contactInformation" required/>
          <button type="submit" >Create Ticket</button>
      </form>
      <p v-if="message">{{ message }}</p>
  </div>
</template>
<script>
  export default{
      data(){
          return{
              ticket: {
                  title: "",
                  description: "",
                  contactInformation: "",
              },
              message: "",
          };
      },
      methods: {
          async submitTicket(){
              try{
                  const response = await fetch("http://localhost:8080/api/tickets/createTicket", {
                  method: "POST",
                  headers: { "Content-Type": "application/json" },
                  body: JSON.stringify(this.ticket),
              });
              if (response.ok) {
                  this.message = "Ticket created successfully!";
                  this.ticket = { title: "", description: "", contactInformation: "" };
              } else {
                  this.message = "Failed to create ticket.";
              }
          } catch (error) {
              console.error("Error:", error);
              this.message = "Error creating ticket.";
          }
      },
  },
  };
</script>
<style>

</style>