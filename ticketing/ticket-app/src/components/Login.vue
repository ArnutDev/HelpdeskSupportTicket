<template>
    <div class="login-box" v-if="!role">
        <h1>Login</h1>
        <div class="button-group">
            <button type="button" class="btn-login" @click="showComponent('user')">Login as User</button>
            <button type="button" class="btn-login supporter-btn" @click="showComponent('supporter')">Login as
                Supporter</button>
        </div>
    </div>

    <div v-if="role === 'user'">
        <CreateTicket />
    </div>
    <div v-if="role === 'supporter'">
        <ManageTicket />
    </div>
</template>

<script>
import CreateTicket from './CreateTicket.vue';
import ManageTicket from './ManageTicket.vue';

export default {
    name: "LoginPage",
    components: {
        CreateTicket,
        ManageTicket
    },
    data() {
        return {
            role: null,
        };
    },
    methods: {
        showComponent(selectedRole) {
            this.role = selectedRole;// set role
        },
    },
};
</script>

<style scoped>
.login-box {
    width: 300px;
    padding: 40px;
    margin: auto;
    background-color: #f4f4f4;
    border-radius: 4px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
}

h1 {
    color: #333;
}

.button-group {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.btn-login {
    flex: 1;
    padding: 12px;
    margin: 5px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    text-align: center;
}

.supporter-btn {
    background-color: #007BFF;
}

.btn-login:hover {
    opacity: 0.9;
}
</style>
