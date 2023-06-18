# Spring Boot Caching
This project demonstrates the use of caching with Redis to speed the request response

## Endpoints

| Endpoint         | Method | Parameters                                                  |
|------------------|--------|-------------------------------------------------------------|
| /categories      | GET    | Retrieve all categories                                     |
| /products        | GET    | Retrieve all products                                       |
| /products/search | GET    | Search products by name, category, price and availability   |
| /products        | POST   | Add a new product                                           |

### Example of key stored in Redis

127.0.0.1:6379> hgetall "cacheData:searchProducts-iphone se-phone-150.0-550.0-yes"
1) "value"
2) "[{\"id\":20,\"name\":\"Apple Iphone SE 2020\",\"description\":\"Product Description 20\",\"price\":429.0,\"createdAt\":1687098024452,\"updatedAt\":1687098024452,\"available\":true}]"
3) "key"
4) "searchProducts-iphone se-phone-150.0-550.0-yes"
5) "_class"
6) "com.bitwise.springbootcaching.models.CacheData"