# README #

Please refer to the various APIs in the non-abstract Controller classes under com.paymentdemo.controller
to find information on specific APIs. However, for basic installation instructions:

To install, Maven package this directory:
    mvn -U package
You can also import the source code into an IDE like any other Maven project and build that way as well.
To run, run:
    java -jar target/payment-request-api-0.0.1.jar

To run curl queries in accordance with the REST APIs you can find in the non-abstract controllers, run:

    (GET):
        curl http://localhost:8080/<apiname>
    (POST):
        curl -d "param1=value1&param2=value2&param3=value3" (and so on)
    (DELETE):
        curl -i -X DELETE http://localhost:8080/<apiname>?param1=value1 (where param1 is the identifier)

    For example, to get a customer from a customer ID of 0, run:
        curl http://localhost:8080/demo/customer?customerId=0
    To add a new customer, run something like (default customer ID is 0, auto incrementing):
        curl -d "firstName=John&lastName=Smith&year=1990&month=January&day=1" http://localhost:8080/demo/customer
    To delete a customer, run:
        curl -i -X DELETE localhost:8080/demo/customer?customerId=0


This application by default uses a H2 Database, located in the folder the Java instance was launched from.
This can be modified by updating application.properties.
Unit tests use an in-memory database.