## JWT example with spring security

### ¿Why this project?
This project is made just for fun and mainly to really understand how JWT works. As many other concepts in the software development universe, technologies, frameworks o just concepts remain sometimes abstract and not easy to understand nearly always is needed to put on practice what you are trying to learn.

### ¿What is a token?
A token is nothing but a string of serialized characters and used for authentication between two parts. 

User send the token to login > Server validates the token and allows to log in 

### ¿What is JWT?
JWT is the acronym of JSON Web Token. It is a standard (RFC 7519) used during the last decade as a way of login. Nowadays, applications are not always monoliths, instead, they are built as API REST microservices. So we don't really have the typical form based login with user and password. And here is where JWT can help the client to tell the server that is logged and can access to some information into it.

JWT is formed by three parts as:

- Header: algorithm used to generate the signature

- Payload: a set of claims. For example the username, the roles, the permissions...etc.
  
- Signature: needed to validate the token

### JWT flow
[![jwt-flow.jpg](https://i.postimg.cc/4xdX3Jxw/jwt-flow.jpg)](https://postimg.cc/HVfqZ157)

1. The client sends the credentials to the server
2. The server validates in database the credentials(for example)
3. The server respond to the client with JWT with the user information, privileges and an expiration time
4. The client can use the JWT in every request to the server until it expires

### Java JWT: JSON Web Token for Java
JJWT is a library often used by Java developer to create and sign JWT and also to verify that a JWT is valid.

You can find a lot of info the [JJWT Github repo](**https://github.com/jwtk/jjwt#jws-read-key**)

We used this library in this project in combination with spring security who provides the login service.