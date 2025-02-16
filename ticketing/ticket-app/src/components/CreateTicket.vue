<template>

    <button class="btn" v-show="!isFormVisible" @click="toggleVisibleForm">Create ticket</button>
    <button class="btn-list" v-show="!isListVisible" @click="toggleVisibleList">Show history</button>
    <GetTicketDetails v-show="isListVisible" />
    <div class="container" v-show="isFormVisible">
        <h2 class="text-topic">Create new ticket</h2>
        <p v-if="message" :class="status">{{ message }}</p>
        <form @submit.prevent="submitTicket">
            <h4><label>Title: </label></h4>
            <input v-model="ticket.title" required />
            <h4><label>Contact information: </label></h4>
            <input v-model="ticket.contactInformation" required />
            <h4><label>Description: </label></h4>
            <textarea v-model="ticket.description" required />
            <button class="btn" type="submit">
                <p>Submit ticket</p>
            </button>
        </form>

    </div>
</template>
<script>
import GetTicketDetails from './GetTicketDetails.vue';
export default {
    name: "CreateTicket",
    components: {
        GetTicketDetails
    },
    data() {
        return {
            ticket: {
                title: "",
                description: "",
                contactInformation: "",
            },
            message: "",
            status: "",
            isFormVisible: false,
            isListVisible: false,
        };
    },
    methods: {
        async submitTicket() {
            try {
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
        toggleVisibleForm() {
            this.isFormVisible = !this.isFormVisible
            if (this.isListVisible) {
                this.isListVisible = !this.isListVisible
            }
        },
        toggleVisibleList() {
            this.isListVisible = !this.isListVisible
            if (this.isFormVisible) {
                this.isFormVisible = !this.isFormVisible
            }
            this.message = ""
        },
    },
};

</script>
<style>
.container {
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

form textarea {
    width: 80%;
    height: 200px;
    padding: 15px;
    border: 1px solid black;
    border-radius: 4px;
    background-color: #dcdcdc;
    font-size: 15px;
}

form {
    max-width: auto;
    margin: auto;
    /* background-color: orange; */
}

h4 {
    /* background-color: red; */
    margin: 20px 0 5px 0;
}

.btn {
    background-color: #0db651;
    color: #dcdcdc;
    border: none;
    margin: 15px 10px 10px 10px;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
}

.btn:hover {
    background-color: #007E33;
}

.btn-list {
    background-color: #0d6efd;
    color: #dcdcdc;
    border: none;
    margin: 15px 10px 10px 10px;
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
}

.btn-list:hover {
    background-color: #1956b1;
}

.success {
    color: #4BB543;
}

.failed {
    color: red;
}

.text-topic {
    color: #eed202;
}
</style>