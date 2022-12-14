# pgsql-populator
A simple PostgreSQL populator for practicing. As usual, suggestions, comments and corrections are welcome!

## Libraries

* Datafaker
* Swagger
* Lombok

## Types and parameters

Each type has its own parameters, including the parameter string type, that will be used for all types. 

I describe below the types available and what parameters are expected for each one of them in the petitions, since this api has only one endpoint to create queries (POST - https://psqlpopulator.herokuapp.com/api/v1/populator/).

Ps.: The endpoint also expects the parameters tableName and recordsNumber.

### CHAR, SMALL_INT, INTEGER, BIG_INT, MONEY, BOOLEAN, UUID

* String name.

```
{
    "name": "character",
    "type": "CHAR"
}
```

### VARCHAR and TEXT

* String name;
* Enum fieldType (FIRST_NAME, LAST_NAME, FULL_NAME, ADDRESS, COMPANY, ID_NUMBER, PHONE_NUMBER);
* Integer length (not mandatory).

```
{
    "length": 10,
    "name": "name",
    "type": "VARCHAR",
    "varcharType": "FIRST_NAME"
} 

{
    "name": "name",
    "type": "VARCHAR",
    "varcharType": "FIRST_NAME"
}
```

### ENUM

* String name;
* String[] items.

```
{
    "items": [
      "banana", "orange", "apple", "coconut", "pear"
    ],
    "name": "fruit",
    "type": "ENUM"
}
```

### DATE, TIME and TIMESTAMP

* String name;
* LocalDateTime startDate;
* LocalDateTime endDate;

```
{
    "name": "date",
    "type": "DATE",
    "startDate": "2022-10-01T10:00:00",
    "endDate": "2022-10-31T10:00:00"
}
```
I also developed a webpage with React. It's available on https://resilient-squirrel-eea92e.netlify.app/
The code is available on https://github.com/daniel-joanol/pgsql-populator-front
