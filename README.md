# Scalable Java Server 

This project explores different approaches to building a **scalable socket-based server in Java** while learning core concepts of **network programming, concurrency, and performance testing**.

The goal was to understand how different server architectures handle **multiple client connections** and how they perform under **high request load**.

To achieve this, I implemented **three different server designs**, each representing a different concurrency model.

---

## Project Overview

In this project, three versions of a TCP server were built:

### 1. Single-Threaded Server
The simplest implementation where the server handles **one client request at a time**.

**Characteristics**
- Easy to implement
- Useful for understanding socket basics
- Not scalable for multiple concurrent clients

**Limitation**
- Requests are processed sequentially
- Other clients must wait until the current request finishes

---

### 2. Multithreaded Server
A traditional approach where the server **creates a new thread for every client connection**.

**Characteristics**
- Handles multiple clients concurrently
- Improves responsiveness

**Limitations**
- Creating and destroying threads is expensive
- High load can cause excessive thread creation
- Increased memory and CPU overhead

---

### 3. Thread Pool Server (Production-Style)
The most efficient version where a **fixed pool of worker threads** processes incoming client requests.

**Characteristics**
- Reuses threads instead of creating new ones
- Much better performance under heavy load
- More stable and scalable architecture

**Benefits**
- Reduced thread creation overhead
- Controlled resource usage
- Suitable for real-world backend systems

---

## Key Concepts Learned

Through this project, I gained practical experience with:

- Java Socket Programming
- Multithreading and Concurrency
- Thread Pool Architecture
- Exception Handling in Network Systems
- Resource Management
- Performance Testing

---

## Exception Handling

While building the server, I encountered several common networking issues:

- **ConnectException** – occurs when a client fails to connect to the server  
- **SocketTimeoutException** – occurs when a socket operation times out

Handling these exceptions correctly helped improve the **reliability and robustness** of the system.

---

## Performance Testing

To evaluate scalability, the server implementations were tested under **high load conditions**.

**Test Setup**

- Load Testing Tool: **Apache JMeter**
- Request Rate: **1000 requests per second (RPS)**
- Goal: Evaluate how each architecture handles concurrent traffic

**Observations**

- The **Single-Threaded server** quickly becomes a bottleneck.
- The **Multithreaded server** handles concurrency better but consumes more resources.
- The **Thread Pool server** provides the best balance between **performance and resource efficiency**.

---

## Project Structure

```
├── MultiThreaded
│   ├── Client.java
│   └── Server.java
│
├── SingleThreaded
│   ├── Client.java
│   └── Server.java
│
├── ThreadPool
│   └── Server.java
│
├── Demo Multithreaded Web Server.mp4
└── README.md
```

---

## How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```

### 2. Compile the Server

```bash
javac Server.java
```

### 3. Run the Server

```bash
java Server
```

### 4. Connect Using a Client

You can connect using tools such as:

- **Telnet**
- **Netcat**
- A custom socket client
- Load testing tools

Example:

```bash
telnet localhost 8080
```

---

## Performance Testing (Optional)

To reproduce the load testing:

1. Start the server
2. Configure **Apache JMeter**
3. Create a **Thread Group**
4. Add TCP/HTTP requests targeting `localhost:PORT`
5. Run tests at **1000 RPS**

---

## Key Takeaways

This project demonstrates how **server architecture choices impact scalability, resource utilization, and reliability**.  

It also provides hands-on experience with **building resilient backend systems using Java concurrency tools**.

