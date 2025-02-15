<template>
    <h2 class="text-topic">Create New Ticket</h2>
    <p v-if="message" :class="status">{{ message }}</p>
    <div class="container">
        <form @submit.prevent="submitTicket">
            <h4><label>Title: </label></h4>
            <input v-model="ticket.title" required/>
            <h4><label>Contact Information: </label></h4>
            <input v-model="ticket.contactInformation" required/>
            <h4><label>Description: </label></h4>
            <textarea  v-model="ticket.description" required/>
            <button class="btn" type="submit" ><p>Create Ticket</p></button>
        </form>
    </div>
    <GetTicketDetails/>
</template>
<script>
import GetTicketDetails from './GetTicketDetails.vue';
    export default{
        name: "CreateTicket",
        components: {
            GetTicketDetails
    },
    data(){
                return{
                    ticket: {
                        title: "",
                        description: "",
                        contactInformation: "",
                    },
                    message: "",
                    status: "",
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
                        this.status = "success";
                        this.ticket = { title: "", description: "", contactInformation: "" };
                    } else {
                        this.message = "Failed to create ticket. Try again!";
                        this.status = "failed";
                    }
                } catch (error) {
                    console.error("Error:", error);
                    this.message = "Error creating ticket. Try again!";
                    this.status = "failed";
                }
            },
        },
    };
        
</script>
<style>
.container{
    /* background-color: green; */
    text-align: center;
    padding: 0 25% 0 25%;
    justify-content: center;
}
form input {
    width: 60%; 
    height: 10px;
    padding: 15px;
    border: 1px solid black;
    border-radius: 4px;
    background-color: #dcdcdc;
    font-size: 15px;
}
form textarea{
    width: 80%;
    height: 200px;
    padding: 15px;
    border: 1px solid black;
    border-radius: 4px;
    background-color: #dcdcdc;
    font-size: 15px;
}

form {
    max-width: 600px; 
    margin: auto;
    /* background-color: orange; */
}
h4{
    /* background-color: red; */
    margin: 20px 0 5px 0;
}
.btn {
    background-color: #00C851; 
    color: white; 
    border: none; 
    margin: 15px 0 10px 0;
    border-radius: 5px; 
    cursor: pointer; 
    font-size: 16px;
    font-weight: bold;
}

.btn:hover {
    background-color: #007E33; 
}
.success{
    color: #4BB543;
}
.failed{
    color: red;
}
.text-topic{
    color: #eed202;
}
</style>