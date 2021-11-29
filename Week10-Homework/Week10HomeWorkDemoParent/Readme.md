Testing:

1. set stock 10000  (In Redis)
2. Launch StockService and Redis-Demo services
3. ab -n 5000 -c 100 localhost:8089/redisTest