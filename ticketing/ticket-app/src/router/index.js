import {
    createRouter,
    createWebHistory
} from "vue-router";
import GetTicketDetails from "../components/GetTicketDetails.vue";
import CreateTicket from "../components/CreateTicket.vue";
import ViewTicket from "../components/ViewTicket.vue";

const routes = [{
        path: "/",
        component: CreateTicket
    },
    {
        path: "/ticket",
        component: GetTicketDetails
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