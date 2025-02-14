<template>
    <div v-if="ticket">
        <h1>Ticket Details</h1>
        <p><strong>Title:</strong> {{ ticket.title }}</p>
    <p><strong>Description:</strong> {{ ticket.description }}</p>
    <p><strong>Contact:</strong> {{ ticket.contactInformation }}</p>
    <p><strong>Status:</strong> {{ ticket.status }}</p>
    <button @click="$router.push('/')">Back</button>
    </div>
    <p v-else>Loading...</p>
</template>
<script>
export default {
    data() {
        return {
            ticket: null,
        };
    },
    async created() {
        const id = this.$route.param.id;
        const response = await fetch(`http://localhost:8080/api/tickets/${id}`);//use `
        this.ticket = await response.json();
},
};
</script>