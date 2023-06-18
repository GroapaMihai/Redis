# Spring Boot Caching
This project demonstrates the use of caching with Redis to speed the request response

## Endpoints

| Endpoint         | Method | Parameters                                                  |
|------------------|--------|-------------------------------------------------------------|
| /categories      | GET    | Retrieve all categories                                     |
| /products        | GET    | Retrieve all products                                       |
| /products/search | GET    | Search products by name, category, price and availability   |
| /products        | POST   | Add a new product                                           |