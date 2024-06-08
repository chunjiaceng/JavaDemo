const http = require("http");

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader("Content-Type", "text/plain");
  res.end("Hello, World!");
});

server.listen("8082", "127.0.0.1", () => {
  console.log("Server Listen in 8082 port");
});
