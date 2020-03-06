# PRODUCT-MANAGER
Product/Orders API developed with Spring Boot

The API was developed using Spring Boot framework, based on a @RestController and with a JPA Repository. In this branch, the database is built on Postgres, which is launched on a docker container.

There is a Spring Boot cache to persist the state of a particular endpoint, in this case the order search endpoint.

I also use mapStruct mappers to map between the services DTO and the model objects.

The project is covered by unit tests, in this case only the service layer, since it's where the business logic is located. It would make more sense to cover controllers by integration tests.

# Installation

This new version uses docker to launch a postgres DB and the spring boot app in different containers. To build it, just call the docker compose command: docker-compose up --build.

You can check API's documentation at http://192.168.99.100:8090/swagger-ui.html and you can run the services easily in Postman importing productorders.postman_collection.json file (The IP can vary and it corresponds to the docker virtual machine IP).

# Next steps

Authentication

Apart from webservices validation, it should be added an authentication method on the client side of the API, so the database resources are not exposed. OAuth protocol is a very used method for REST APIs. Version 2 of this protocol made things easier, without the need of signing each call with a keyed hash, requiring only an access token or a refresh token, if we configure the tokens to expire. Besides this, OAuth is a good choice for identifying personal user accounts and granting proper permissions and it is very straightforward to implement with the Spring Boot security layer.

 Redundancy
 
If we want to scale this application and make it a high availability system, it's important to consider redudancy in the design of the system. DoS attacks can explore different vulnerabities in the system and take down entire servers, so it's important to design a clustering system with a load balancing switch to alternate between the different physical servers. Services like AWS allow elastic load balancing that automatically distributes traffic.






