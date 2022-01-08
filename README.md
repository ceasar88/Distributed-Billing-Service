# Implementing Billing Services on a Hadoop Distributed System

We implemented a billing service on distributed systems where students are the clients that want to send their payments to their university which hosts a server comprised of a local and cloud hadoop node. The payment request sent by the student includes their full name and the payment amount. Through the use of sockets, the client sends these payment requests to the server. The server then performs verification of student information and payment before updating the student’s fee record (stored in a CSV file) accordingly and sends a confirmation upon success.

### Please see Group report for full documentation!
