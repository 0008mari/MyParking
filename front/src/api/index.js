import axios from "axios";

const appClient = axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  timeout: 3000,
});

export { appClient };
