# pgsql-populator
A simple PostgreSQL populator for practicing. As usual, suggestions, comments and corrections are welcome!

## Libraries

* Datafaker
* Swagger
* Lombok

## Types and parameters

Each variable type has its own parameters.

One of these parameters is the string fieldType, that will be used for all types. 

I describe below the field types available and what parameters are expected for each one of them in the petitions, since this api has only one endpoint to create queries.

### CHAR, SMALL_INT, INTEGER, BIG_INT, MONEY, BOOLEAN, UUID

* String name.

### VARCHAR

* String name;
* Enum fieldType (FIRST_NAME, LAST_NAME, FULL_NAME, ADDRESS, COMPANY, ID_NUMBER, PHONE_NUMBER);
* Integer length.

### TEXT

* String name;
* Enum fieldType (FIRST_NAME, LAST_NAME, FULL_NAME, ADDRESS, COMPANY, ID_NUMBER, PHONE_NUMBER);
* Integer length.

### ENUM

* String name;
* String[] items.

### DATE, TIME and TIMESTAMP

* String name;
* LocalDateTime startDate;
* LocalDateTime endDate;