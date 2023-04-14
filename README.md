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
- password is 

### How to login

1. Get an access token
```bash
curl --location 'http://localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "user",
    "password": "hellohello"
}'
```
2. Then use access token in API calls
```bash
curl --location 'http://localhost:8080/api/v1/employee/detail?id=1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgxNDg5NDQyLCJleHAiOjE2ODE0ODk0OTJ9.Ei04_cOfjVbfNKWuuB2Wlzj3PYZdQvUDkXTsqenkYmEew6PnmaP0h4XV9-7evx3ag6JAlGscTcSTAKio_LUdiA'
```

### To Do's

- add JWT security - done
- add Swagger
- add Dockerfile
- add Unit testing
- add MySQL connection
- add update and delete operations