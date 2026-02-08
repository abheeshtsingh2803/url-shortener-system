import { useState } from "react";
import { shortenUrl } from "../services/api";
import { isValidUrl } from "../utils/validators";
import ShortUrlResult from "./ShortUrlResult";

export default function UrlShortener() {
  const [longUrl, setLongUrl] = useState("");
  const [shortUrl, setShortUrl] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleShorten = async () => {
    setError("");
    setShortUrl("");

    if (!isValidUrl(longUrl)) {
      setError("Please enter a valid URL");
      return;
    }

    try {
      setLoading(true);
      const response = await shortenUrl(longUrl);
      setShortUrl(response.data.shortUrl);
    } catch (err) {
      setError("Failed to shorten URL");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <h1>🔗 URL Shortener</h1>

      <input
        type="text"
        placeholder="Enter long URL"
        value={longUrl}
        onChange={(e) => setLongUrl(e.target.value)}
      />

      <button onClick={handleShorten} disabled={loading}>
        {loading ? "Shortening..." : "Shorten URL"}
      </button>

      {error && <p className="error">{error}</p>}

      {shortUrl && <ShortUrlResult shortUrl={shortUrl} />}
    </div>
  );
}