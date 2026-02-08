export default function ShortUrlResult({ shortUrl }) {
  const copyToClipboard = () => {
    navigator.clipboard.writeText(shortUrl);
    alert("Copied to clipboard!");
  };

  return (
    <div className="result">
      <p>
        Short URL:{" "}
        <a href={shortUrl} target="_blank" rel="noreferrer">
          {shortUrl}
        </a>
      </p>

      <button onClick={copyToClipboard}>Copy</button>
    </div>
  );
}