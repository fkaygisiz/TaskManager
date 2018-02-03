# Spring Boot app & Postgresql & Docker compose

## 1. Build app & Dockerfile

`mvn clean install`

## 2. Run docker-compose

`cd src/main/docker`

`docker-compose up`

**What happens:**

1. Starts Postgresql and waits up to 15 seconds for it to finish ([using wait-for-it](https://github.com/vishnubob/wait-for-it))
2. Starts Spring boot application which populates database with some test data

## 3. Test

Navigate to <http://localhost:8080/tasks> (set header "Content-Type":"application/json") and you should see something similar to: 
`[
    {
        "createdAt": "2018-02-03T13:09:16",
        "updatedAt": "2018-02-03T13:09:16",
        "dueDate": "2018-02-08T00:00:00",
        "resolvedAt": null,
        "title": "title9.611398433516547",
        "description": "description 9.611398433516547",
        "priority": "HIGH",
        "status": "COMPLETED",
        "postponedDate": null,
        "id": "628127cb-41f4-4bf2-adcb-6920f9ea5bb2"
    },
    {
        "createdAt": "2018-02-03T13:09:23",
        "updatedAt": "2018-02-03T13:09:23",
        "dueDate": "2018-02-14T00:00:00",
        "resolvedAt": "2018-02-03T13:09:23",
        "title": "title9.677457449069946",
        "description": "description 9.677457449069946",
        "priority": "LOW",
        "status": "DEFINED",
        "postponedDate": null,
        "id": "a40790fa-b262-4b3b-b0b7-d7b5a9dbec43"
    }
]`