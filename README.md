# Hypermedia

A simple Hypermedia REST API build with Java Spring.

This API is a simple online shop demo where users can register, login, view and "buy" products.

## Usage

To try out this demo just build and run the project and then open the hal explorer
(http://localhost:8080/explorer/index.html#theme=Darkly&uri=http://localhost:8080/api).

Then use the following headers for the explorer to work properly, where token is returned after the login/registration process. 

    accept         application/hal+json
    authorization  {token}

## Examples

create new product (Post):

    {
      "name": "Kiwi",
      "amountInStock": 5,
      "price": 1.5,
      "available": true
    }
    
update existing product (Patch):

    {
      "amountInStock": 20,
      "price": 0.5,
      "available": true
    }
