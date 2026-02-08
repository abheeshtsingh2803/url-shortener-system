const API_BASE_URL =
  process.env.REACT_APP_API_URL || "http://localhost:8080";

export const shortenUrl = async (longUrl) => {
  return fetch(`${API_BASE_URL}/api/v1/shorten`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ longUrl }),
  }).then((res) => res.json());
};