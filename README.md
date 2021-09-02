## Getting started

### 1. Start the infrastructure

```
# pwd: /isidore
docker-compose up
```

### 2. Run the app

```
#pwd /isidore
./gradlew bootRun
```

### GraphiQL

This tool helps developers playing around with the GraphQL endpoint.

```
localhost:8080
```

#### Sample query

```graphql
mutation {
  user(email: "khoi@mail.com", name: {firstName: "Khoi", lastName: "Hoang"}) {
    id
    email
    name {
      baptismalName
      firstName
      middleName
      lastName
    }
  }
}
```

```graphql
query {
  user(id: "61310b7f26d1447c63414944") {
    id
    email
    name {
      baptismalName
      firstName
      middleName
      lastName
    }
  }
}
```

## Testing

### Integration test

* For now, needs `docker-compose up` to initiate the testing infrastructure.

## Development resources

* https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/
* https://dzone.com/articles/a-beginners-guide-to-graphql-with-spring-boot
