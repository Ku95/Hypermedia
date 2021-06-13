# Examples

## HAL

    {
      "id": 1,
      "name": "Apple",
      "amountInStock": 3,
      "price": 2.5,
      "available": true,
      "_links": {
        "self": {
          "href": "http://localhost:8080/api/products/1"
        },
        "products": {
          "href": "http://localhost:8080/api/products"
        },
        "buy": {
          "href": "http://localhost:8080/api/products/1/buy"
        }
      }
    }
    
## Siren

    {
      "properties": { 
      "id": 1,
      "name": "Apple",
      "amountInStock": 3,
      "price": 2.5,
      "available": true
    },
      "actions": [
        {
          "name": "buy",
          "method": "GET",
          "href": "http://localhost:8080/api/products/1/buy"
        },
        {
          "name": "update",
          "method": "PATCH",
          "href": "http://localhost:8080/api/products/1",
          "fields": [
            { "name": "name", "type": "text" },
            { "name": "amountInStock", "type": "number" },
            { "name": "price", "type": "number" },
            { "name": "available", "type": "checkbox" }
          ]
        }
      ],
      "links": [
        { "rel": [ "self" ], "href": "http://localhost:8080/api/products/1" },
        { "rel": [ "products" ], "href": "http://localhost:8080/api/products" }
      ]
    }
    
## JSON API

    {
      "links": {
        "products": "http://localhost:8080/api/products"
      },
      "data": {
  	    "id": 1,
        "type": "product",
        "attributes": {
          "name": "Apple",
          "amountInStock": 3,
          "price": 2.5,
          "available": true
        },
        "links": {
          "self": "http://localhost:8080/api/products/1",
          "buy": "http://localhost:8080/api/products/1/buy"
  	    }
      }
    }
