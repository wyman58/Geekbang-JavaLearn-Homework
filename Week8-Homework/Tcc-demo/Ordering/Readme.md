Usage:

All the modules run in Java 11
1. Start up Eureka, port 8761
2. Start up Ordering Service, port 8081
3. Start up Shipping Service, port 8082

To simulate the TCC
Make Order request then update order and shipping data in transaction.