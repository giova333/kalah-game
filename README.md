[![Build Status](https://travis-ci.org/giova333/kalah-game.svg?branch=master)](https://travis-ci.org/giova333/kalah-game)
[![codecov.io](https://codecov.io/gh/giova333/kalah-game/branch/master/graphs/badge.svg?branch=master)](https://codecov.io/github/giova333/kalah-game?branch=master)

# Kalah
Java RESTful Web Service that runs a game of 6-stone Kalah.

#### API

Web Service is deployed on Amazon EC2 instance.

- Swagger API documentation: http://ec2-35-176-241-35.eu-west-2.compute.amazonaws.com:8080/swagger-ui.html#/

- Create Game: 

```
curl --header "Content-Type: application/json" --request POST http://ec2-35-176-241-35.eu-west-2.compute.amazonaws.com:8080/games
```

- Make a move:
```
curl --header "Content-Type: application/json" --request PUT http://ec2-35-176-241-35.eu-west-2.compute.amazonaws.com:8080/games/{gameId}/pits/{pitId}
```

# How to run
If you want to run project on your local machine set dev profile and type the following command from the root directory:

```
mvn clean install
```