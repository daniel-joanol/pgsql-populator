# pgsql-populator
A simple PostgreSQL populator for practicing. As usual, suggestions, comments and corrections are welcome!

## Libraries

* EasyRandom
* JavaFaker
* Swagger
* Lombok

## Types and parameters

Since the request body needs to have a single object, I decided to create a single class called GenericType with every posible parameter that the fields need.

Some of these fields are the enum fieldType, that will be used for all types. 

I describe below the field types available and what parameters from GenericType they actually use.

### CHAR

* String name;
* Boolean unique;

### VARCHAR

* String name;
* Boolean unique;
* enum FieldType (FIRST_NAME, LAST_NAME, FULL_NAME, ADDRESS, COMPANY, ID_NUMBER, PHONE_NUMBER)
