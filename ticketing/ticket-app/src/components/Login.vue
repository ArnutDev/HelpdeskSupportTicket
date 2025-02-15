<template>
    <div v-show="!loggedIn">
        <div class="login-box">
            <h1>Login</h1>
            <form @submit.prevent="handleLogin">
                <div class="textbox">
                    <input type="text" v-model="username" placeholder="Username" required />
                </div>
                <div class="textbox">
                    <input type="password" v-model="password" placeholder="Password" required />
                </div>
                <button type="submit" class="btn-login">Login</button>
            </form>
            <p v-if="loginError" class="error-msg">Invalid username or password!</p>
        </div>
    </div>
    <div v-if="loggedIn">
        <CreateTicket />
    </div>
</template>

<script>
import CreateTicket from './CreateTicket.vue';

export default {
    name: "LoginPage",
    components: {
        CreateTicket,
    },
    data() {
        return {
            username: "",
            password: "",
            loginError: false,
            loggedIn: true,
        };
    },
    methods: {
        async handleLogin() {
            const credentials = { username: this.username, password: this.password };
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials),
            });

            if (response.ok) {
                this.loggedIn = true;
                this.loginError = false;
            } else {
                this.loginError = true;
            }
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
}

h1 {
    text-align: center;
    color: #333;
}

.textbox {
    margin-bottom: 15px;
    width: 100%;
}

.textbox input {
    padding: 10px;
    margin: 5px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.btn-login {
    padding: 12px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}

.btn-login:hover {
    background-color: #45a049;
}

.error-msg {
    color: red;
    font-size: 14px;
    text-align: center;
}

h2 {
    text-align: center;
    color: #333;
}
</style>