import axios from "axios";
import { getToken } from "./AuthService";

const REST_API_URL_BASE = "http://localhost:8080/api/1.0/todos";

axios.interceptors.request.use(function (config) {
  config.headers["Authorization"] = getToken();
  return config;
}, function (error) {
  // Do something with request error
  return Promise.reject(error);
});

export const getAllTodos = async () => {
  return await axios.get(`${REST_API_URL_BASE}`);
};

export const createTodo = async (todo) => {
  return await axios.post(`${REST_API_URL_BASE}`, todo);
};

export const getTodo = async (id) => {
  return await axios.get(`${REST_API_URL_BASE}/${id}`)
}

export const updateTodo = async (id, todo) => {
  return await axios.put(`${REST_API_URL_BASE}/${id}`,todo)
}

export const deleteTodo = async (id) => {
  return await axios.delete(`${REST_API_URL_BASE}/${id}`)
}

export const completeTodo = async (id) => {
  return await axios.patch(`${REST_API_URL_BASE}/${id}/complete`)
}

export const inCompleteTodo = async (id) => {
  return await axios.patch(`${REST_API_URL_BASE}/${id}/incomplete`)
}