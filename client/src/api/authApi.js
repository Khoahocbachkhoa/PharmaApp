import axiosClient from "./axios";

export const loginApi = (username, password) => {
  return axiosClient.post(`/login`, {
    "username": username,
    "password": password
  });
};