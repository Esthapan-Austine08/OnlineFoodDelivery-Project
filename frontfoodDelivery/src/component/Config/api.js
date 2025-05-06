import axios from "axios";

export const API_URL="http://localhost:6060/FoodDelivery-OnlineProject"

export const api=axios.create({
    baseURL:API_URL,
    headers:{
         "Content-Type":"application/json"
    }

})