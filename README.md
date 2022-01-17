# Sportsbook Service
This project is build using Spring boot. You must have at least Java 11 and maven installed to build and run this project.

Swagger Definitions are available at the below URL

http://localhost:8080/swagger-ui/
## Persistanse Layer
H2 is used as a DB. It stores the data to a file locally.

## Build
The project uses maven for build.

`mvn clean install`
### Running Unit Tests only
`mvn test`

### Run
Run as springboot application 

`mvn spring-boot:run`
### Running Acceptance Tests only
You must run the service before running the acceptance tests.

`mvn test -Dtest=AcceptanceRunner`

Acceptance Tests report will be generated at
./target/karate-reports/karate-summary.html

#How to use
## Notifications
Latest scoreboard update is available on the following URL

http://localhost:8080/

### Get all Scoreboards
Use endpoint below to fetch a list of all scoreboards

http://localhost:8080/scoreboards

`curl -X GET "http://localhost:8080/scoreboards" -H "accept: */*"`
### Get a specific Scoreboard
Use endpoint below to fetch a specific scoreboard

http://localhost:8080/scoreboard/1

`curl -X GET "http://localhost:8080/scoreboard/1" -H "accept: */*"`
### Create a new Scoreboard
Use endpoint below to create a new scoreboard. While creating, Id must be 0/null

http://localhost:8080/scoreboard

`curl -X POST "http://localhost:8080/scoreboard" -H "accept: */*" -H "Content-Type: application/json" -d "{\"eventTitle\":\"string\",\"id\":0,\"teamAScore\":0,\"teamBScore\":0}"`
### Create update an existing Scoreboard
Use endpoint below to update an existing scoreboard. While creating, Id must not be 0/null

http://localhost:8080/scoreboard/1

`curl -X PUT "http://localhost:8080/scoreboard/1" -H "accept: */*" -H "Content-Type: application/json" -d "{\"id\":1,\"eventTitle\":\"string\",\"teamAScore\":10,\"teamBScore\":0}"`
