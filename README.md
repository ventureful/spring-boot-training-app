# Employee API

### How to run
```bash
$ mvn spring-boot:run

# use maven wrapper if no maven installed
$ ./mvnw spring-boot:run
```

### How to query H2 database

- go to http://localhost:8080/h2-console
- pick `Generic H2 (Embedded)`
- set JDBC URL as `jdbc:h2:file:~/Downloads/morivy/research/employee/data/employee`
- username as `sa`
- password is empty

### To Do's

- add JWT security
- add Swagger
- add Dockerfile
- add Unit testing
- add MySQL connection
- add update and delete operations