import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

export const shortenUrl = async (longUrl) => {
  return axios.post(`${API_BASE_URL}/api/v1/shorten`, {
    longUrl: longUrl,
  });
};