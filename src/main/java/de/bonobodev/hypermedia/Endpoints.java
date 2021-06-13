package de.bonobodev.hypermedia;

public class Endpoints {

    /*
    accept         application/hal+json
    authorization  Token

    {
      "name": "Kiwi",
      "amountInStock": 5,
      "price": 1.5,
      "available": true
    }

    {
      "amountInStock": 20,
      "price": 0.5,
      "available": true
    }
    */

    public static final String API = "/api";
    public static final String EXPLORER = "/explorer/**";

    public static final String USER = "/api/user";
    public static final String CURRENT = "/api/user/current";
    public static final String LOGIN = "/api/user/login";
    public static final String LOGOUT = "/api/user/logout";
    public static final String REGISTER = "/api/user/register";

    public static final String PRODUCTS = "/api/products";
    public static final String PRODUCT = "/api/products/{id}";
    public static final String BUY = "/api/products/{id}/buy";
}
