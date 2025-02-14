import {
    createRouter,
    createWebHistory
} from "vue-router";
import GetData from "../components/GetData.vue";
import CreateTicket from "../components/CreateTicket.vue";
import ViewTicket from "../components/ViewTicket.vue";

const routes = [{
        path: "/",
        component: GetData
    },
    {
        path: "/create",
        component: CreateTicket
    },
    {
        path: "/ticket/:id",
        component: ViewTicket
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;