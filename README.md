## JWT example with spring security

### ¿What is a token?

A token is nothing but a string of characters usually encoded and used for authentication between two parts. 

User send the token to login > Server validates the token and allows to log in 

### ¿What is JWT?

JWT is the acronym of JSON Web Token. It is a standard (RFC 7519) very common already. It is a token, but it is not a simple one. It has three parts divided with dots.

- Header: algorithm used to generate the signature
- Payload: a set of claims. For example the username, the roles, the permissions...etc.
- Signature: needed to validate the token

### JWT flow
[![jwt-flow.jpg](https://i.postimg.cc/4xdX3Jxw/jwt-flow.jpg)](https://postimg.cc/HVfqZ157)
