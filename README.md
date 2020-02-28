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




