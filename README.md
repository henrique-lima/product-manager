# PRODUCT-MANAGER
Product/Orders API developed with Spring Boot

The API was developed using Spring Boot framework, based on a @RestController and with a JPA Repository connected to a H2 in memory database. This was the choice for persistency since we are in a dev environment.

There is a Spring Boot cache to persist the state of a particular endpoint, in this case the order search endpoint.

I also use mapStruct mappers to map between the services DTO and the model objects.

The project is covered by unit tests, in this case only the service layer, since it's where the business logic is located. It would make more sense to cover controllers by integration tests.

# Installation

To run the application, just launch windows batch file "init.bat" or the equivalent for linux "init.sh". The webservices will be exposed on port 8090.

You can check API's documentation at http://localhost:8090/swagger-ui.html and you can run the services easily in Postman importing productorders.postman_collection.json file.

# Next steps

Authentication

Apart from webservices validation, it should be added an authentication method on the client side of the API, so the database resources are not exposed. OAuth protocol is a very used method for REST APIs. Version 2 of this protocol made things easier, without the need of signing each call with a keyed hash, requiring only an access token or a refresh token, if we configure the tokens to expire. Besides this, OAuth is a good choice for identifying personal user accounts and granting proper permissions and it is very straightforward to implement with the Spring Boot security layer.

 Redundancy
 
If we want to scale this application and make it a high availability system, it's important to consider redudancy in the design of the system. DoS attacks can explore different vulnerabities in the system and take down entire servers, so it's important to design a clustering system with a load balancing switch to alternate between the different physical servers. Services like AWS allow elastic load balancing that automatically distributes traffic.






