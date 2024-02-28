import axios from "axios";

const AUTH_REST_API_URL_BASE = "http://localhost:8080/api/1.0/auth";

export const registerUser = async (user) => {
  return await axios.post(`${AUTH_REST_API_URL_BASE}/register`, user);
};

export const loginUser = async (user) => {
  return await axios.post(`${AUTH_REST_API_URL_BASE}/login`, user);
};

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

export const saveLoggedInUser = (username,role) => {
  sessionStorage.setItem("authenticatedUser", username);
  sessionStorage.setItem("roleUser", role);
};

export const isUserLoggedIn = () => {
  const username = sessionStorage.getItem("authenticatedUser");
  if (username == null) {
    return false;
  } else {
    return true;
  }
};

export const getLoggedInUser = () => {
  return sessionStorage.getItem("authenticatedUser");
};

export const getLoggedInUserRole = () => {
  return sessionStorage.getItem("roleUser");
};

export const logout = () => {
  sessionStorage.clear();
  localStorage.clear();
};
