# pgsql-populator
A simple PostgreSQL populator for practicing. As usual, suggestions, comments and corrections are welcome!

## Libraries

* Datafaker
* Swagger
* Lombok

## Types and parameters

Each variable type has its own parameters.

One of these parameters is the string type, that will be used for all types. 

I describe below the types available and what parameters are expected for each one of them in the petitions, since this api has only one endpoint to create queries.

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
